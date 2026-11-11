package com.example.musicapp.playback
import android.content.ComponentName
import android.content.Context
import androidx.media3.common.Player
import androidx.media3.common.MediaItem
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.musicapp.data.*
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.guava.await

class PlayerConnection(context : Context){
    private val appContext = context.applicationContext
    private var controller : MediaController? = null
    private var library : List<Song> = emptyList()
    private val _currentSong = MutableStateFlow<Song?>(null)
    private val currentSong = _currentSong.asStateFlow()
    private var _isPlaying = MutableStateFlow(false)
    private var isPlaying = _isPlaying.asStateFlow()
    private val _duration = MutableStateFlow(0L)
    private val _location = MutableStateFlow(0L)
    private val duration = _duration.asStateFlow()
    private val location = _location.asStateFlow()

    suspend fun connect(){
        var token = SessionToken(appContext, ComponentName(appContext, PlaybackService::class.java));
        controller = MediaController.Builder(appContext, token).buildAsync().await()
        controller?.addListener(
            object : Player.Listener{
                override fun onPositionDiscontinuity(
                    oldPosition: Player.PositionInfo,
                    newPosition: Player.PositionInfo,
                    reason: Int
                ) {
                    refreshPosition()
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    _isPlaying.value = isPlaying
                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    updateCurrent()
                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    updateCurrent()
                }
            }
        )
        updateCurrent()
    }
    fun setLibrary(songs : List<Song>){
        this.library = songs
    }
    fun playSong(song : Song){
        val player = controller ?: return
        val index = library.indexOfFirst { it.id == song.id }
        if(index < 0) return
        val mediaItems = library.map{
            item->
            MediaItem.Builder().setMediaId(item.id.toString()).setUri(item.uri).setMediaMetadata(androidx.media3.common.MediaMetadata.Builder().setArtist(item.song_artist).setTitle(item.song_title).setAlbumTitle(item.song_album).build()).build()
        }
        player.setMediaItems(mediaItems, index, 0L)
        player.prepare()
        player.play()
        updateCurrent()
    }
    fun playPause(){
        val player = controller?.let{
            if(it.isPlaying)
                it.pause()
            else
                it.play()
        }
    }
    fun next(){
        controller?.seekToNextMediaItem()
    }
    fun previous(){
        controller?.seekToPreviousMediaItem()
    }
    fun seekTo(positionMs : Long){
        controller?.seekTo(positionMs)
    }
    fun updateCurrent(){
        val player = controller ?: return
        val mediaId = player.currentMediaItem?.mediaId
        _currentSong.value = library.firstOrNull{it.id.toString() == mediaId}
    }
    fun refreshPosition(){
        val player = controller ?: return
        _location.value = player.currentPosition
        _duration.value = player.duration.coerceAtLeast(0L)
        updateCurrent()
    }
}