package com.example.musicapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.playback.*
import com.example.musicapp.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class MusicUIState(
    val is_loading : Boolean = false,
    val songs : List<Song> = emptyList(),
    val favorites : Set<Long> = emptySet(),
    val playlists : List<PlaylistEntity> = emptyList(),
    val query : String = "",
    val error : String? = null
)

class MusicViewModel(
    private val musicRepository: MusicRepository,
    private val dao : LibraryDao,
    private val player : PlayerConnection
): ViewModel(){
    private val _state = MutableStateFlow(MusicUIState())
    private val state = _state.asStateFlow()
    val visible_songs : StateFlow<List<Song>> = _state.map{currentState ->
        currentState.songs.filter{song->
            song.song_title.contains(currentState.query, ignoreCase = true) ||
            song.song_artist.contains(currentState.query, ignoreCase = true) ||
            song.song_album.contains(currentState.query, ignoreCase = true)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    init{
        viewModelScope.launch {
            player.connect()
            refreshLibrary()
            refreshSavedData()
        }
    }
    fun setQuery(query : String){
        _state.update{
            it.copy(query = query)
        }
    }
    fun refreshLibrary(){
        viewModelScope.launch{
            _state.update{
                it.copy(
                    is_loading = true,
                    error = null
                )
            }
            try{
                val songs = musicRepository.scanDevice()
                player.setLibrary(songs)
                _state.update{
                    it.copy(
                        is_loading = false,
                        songs = songs
                    )
                }
            }catch(exception : Exception){
                _state.update{
                    it.copy(
                        is_loading = false,
                        error = exception.message ?: "Unable to load any music."
                    )
                }
            }
        }
    }

    suspend fun refreshSavedData(){
        viewModelScope.launch {
            _state.update{
                it.copy(
                    favorites = dao.favoriteIds().toSet(),
                    playlists = dao.playlists()
                )
            }
        }
    }

    fun toggleFavorite(song : Song){
        viewModelScope.launch{
            if(song.id in _state.value.favorites){
                dao.deleteFavorite(song.id)
            }
            else{
                dao.addFavorite(FavoriteSong(song.id))
            }
            refreshSavedData()
        }
    }

    fun createPlaylist(name : String){
        if(name.isBlank()) return
        viewModelScope.launch {
            dao.createPlaylists(PlaylistEntity(name = name.trim()))
            refreshSavedData()
        }
    }

    fun addToPlaylist(playlistId : Long, song : Song){
        viewModelScope.launch {
            val ids = dao.playlistSongs(playlistId)
            if(song.id !in ids){
                dao.addToPlaylist(
                    PlaylistSong(
                        playlistId = playlistId,
                        songId = song.id,
                        position = ids.size
                    )
                )
            }
        }
    }

    fun deletePlaylist(playlistId : Long){
        viewModelScope.launch {
            dao.deletePlaylists(playlistId)
            refreshSavedData()
        }
    }

    fun favoriteSongs() : List<Song>{
        return _state.value.songs.filter{
            it.id in _state.value.favorites
        }
    }

    override fun onCleared(){
        player.release()
        super.onCleared()
    }
}