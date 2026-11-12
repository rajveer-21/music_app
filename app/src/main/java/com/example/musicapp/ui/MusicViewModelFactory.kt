package com.example.musicapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.example.musicapp.playback.*
import com.example.musicapp.data.*

class MusicViewModelFactory(
    private val musicRepository: MusicRepository,
    private val dao : LibraryDao,
    private val player : PlayerConnection
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MusicViewModel::class.java)){
            return MusicViewModel(musicRepository, dao, player) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}