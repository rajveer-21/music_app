package com.example.musicapp.playback
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.C
import androidx.media3.common.AudioAttributes

class PlaybackService : MediaSessionService(){
    private var mediaSession : MediaSession? = null
    private  lateinit var player : ExoPlayer
    override fun onCreate(){
        super.onCreate()
        player = ExoPlayer.Builder(this).build().apply{
            setAudioAttributes(
                AudioAttributes.Builder().setUsage(C.USAGE_MEDIA).setContentType(C.AUDIO_CONTENT_TYPE_MUSIC).build(), true)
            setHandleAudioBecomingNoisy(true)
        }
        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onGetSession(controllerInfo : MediaSession.ControllerInfo) : MediaSession?{
        return mediaSession
    }

    override fun onDestroy(){
        mediaSession?.release()
        player.release()
        mediaSession = null
        super.onDestroy()
    }
}

class PlaybackService2 : MediaSessionService(){
    private var mediaSession : MediaSession? = null
    private lateinit var player : ExoPlayer
    override fun onCreate(){
        super.onCreate()
        player = ExoPlayer.Builder(this).build().apply{
            setAudioAttributes(AudioAttributes.Builder().setUsage(C.USAGE_MEDIA).setContentType(C.AUDIO_CONTENT_TYPE_MUSIC).build(), true)
            setHandleAudioBecomingNoisy(true)
        }
        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onDestroy() {
        mediaSession?.release()
        player.release()
        mediaSession = null
        super.onDestroy()
    }
}

class PlaybackService3 : MediaSessionService(){
    private var mediaSession : MediaSession? = null
    private lateinit var player : ExoPlayer
    override fun onCreate(){
        super.onCreate()
        player = ExoPlayer.Builder(this).build().apply{
            setAudioAttributes(
                AudioAttributes.Builder().setUsage(C.USAGE_MEDIA).setContentType(C.AUDIO_CONTENT_TYPE_MUSIC).build(), true
            )
            setHandleAudioBecomingNoisy(true)
        }
        mediaSession = MediaSession.Builder(
            this,
            player).build()
    }

    override fun onGetSession(controllerInfo : MediaSession.ControllerInfo): MediaSession?{
        return mediaSession
    }

    override fun onDestroy(){
        mediaSession?.release()
        player.release()
        mediaSession = null
        super.onDestroy()
    }
}

class PlaybackService4 : MediaSessionService(){
    private var mediaSession : MediaSession? = null
    private lateinit var player : ExoPlayer

    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build().apply {
            setAudioAttributes(
                AudioAttributes.Builder().setUsage(C.USAGE_MEDIA).setContentType(C.AUDIO_CONTENT_TYPE_MUSIC).build(), true
            )
            setHandleAudioBecomingNoisy(true)
        }
        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onDestroy()
    {
        mediaSession?.release()
        player.release()
        mediaSession = null
        super.onDestroy()
    }
}

class PlaybackService5 : MediaSessionService(){
    private var mediaSession : MediaSession? = null
    private lateinit var player : ExoPlayer
    override fun onCreate(){
        super.onCreate()
        player = ExoPlayer.Builder(this).build().apply{
            setAudioAttributes(
                AudioAttributes.Builder().setUsage(C.USAGE_MEDIA).setContentType(C.AUDIO_CONTENT_TYPE_MUSIC).build(), true
            )
            setHandleAudioBecomingNoisy(true)
        }
        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onDestroy() {
        mediaSession?.release()
        player.release()
        mediaSession = null
        super.onDestroy()
    }
}