package com.example.musicapp.data
import android.content.Context
import android.content.ContentUris
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicRepository(private val context: Context){
    suspend fun scanDevice() : List<Song> = withContext(Dispatchers.IO){
        val collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.IS_MUSIC
        )
        val songs = mutableListOf<Song>()
        context.contentResolver.query(
            collection,
            projection,
            "${MediaStore.Audio.Media.IS_MUSIC} != 0",
            null,
            "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC"
        )?.use{cursor->
            val idCol = cursor.getColumnIndexOrThrow(
                MediaStore.Audio.Media._ID
            )
            val titleCol = cursor.getColumnIndexOrThrow(
                MediaStore.Audio.Media.TITLE
            )
            val artistCol = cursor.getColumnIndexOrThrow(
                MediaStore.Audio.Media.ARTIST
            )
            val albumCol = cursor.getColumnIndexOrThrow(
                MediaStore.Audio.Media.ALBUM
            )
            val durationCol = cursor.getColumnIndexOrThrow(
                MediaStore.Audio.Media.DURATION
            )
            val albumIdCol = cursor.getColumnIndexOrThrow(
                MediaStore.Audio.Media.ALBUM_ID
            )
            while(cursor.moveToNext()){
                val id = cursor.getLong(idCol)
                songs.add(Song(
                    id = id,
                    song_title = cursor.getString(titleCol) ?: "Unknown Title",
                    song_artist = cursor.getString(artistCol) ?: "Unknown Artist",
                    song_album = cursor.getString(albumCol) ?: "Unknown Album",
                    duration = cursor.getLong(durationCol),
                    uri = ContentUris.withAppendedId(collection, id),
                    albumId = cursor.getLong(albumIdCol)
                ))
            }
        }
        songs
    }
}


class MusicRepository2(private val context : Context){
    suspend fun scanDevice() : List<Song2> = withContext(Dispatchers.IO){
        val collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.IS_MUSIC
        )
        val songs = mutableListOf<Song2>()
        context.contentResolver.query(
            collection,
            projection,
            "${MediaStore.Audio.Media.IS_MUSIC} != 0",
            null,
            "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC"
        )?.use { cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val durationCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val albumIdCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            while(cursor.moveToNext()){
                val id = cursor.getLong(idCol)
                Song(
                    id = id,
                    song_title = cursor.getString(titleCol),
                    song_artist = cursor.getString(artistCol),
                    song_album = cursor.getString(albumCol),
                    duration = cursor.getLong(durationCol),
                    uri = ContentUris.withAppendedId(collection, id),
                    albumId = cursor.getLong(albumIdCol)
                )
            }
        }
        songs
    }
}

class MusicRepository3(private val context : Context){
    suspend fun scanMusic() : List<Song3> = withContext(Dispatchers.IO){
        val songs = mutableListOf<Song3>()
        val collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.IS_MUSIC
        )
        context.contentResolver.query(
            collection,
            projection,
            "${MediaStore.Audio.Media.IS_MUSIC} != 0",
            null,
            "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC"
        )?.use{cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val durationCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val albumIdCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            while(cursor.moveToNext()){
                val id = cursor.getLong(idCol)
                Song3(
                    id = id,
                    song_title = cursor.getString(titleCol) ?: "Unknown Title",
                    song_artist = cursor.getString(artistCol) ?: "Unknown Artist",
                    song_album =  cursor.getString(albumCol) ?: "Unknown Album",
                    duration = cursor.getLong(durationCol),
                    uri = ContentUris.withAppendedId(collection, id),
                    albumId = cursor.getLong(albumIdCol)
                )
            }
        }
        songs
    }
}

class MusicRepository4(private val context : Context){
    suspend fun scanMusic() : List<Song4> = withContext(Dispatchers.IO){
        val songs = mutableListOf<Song4>()
        val collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.IS_MUSIC
        )
        context.contentResolver.query(
            collection,
            projection,
            "${MediaStore.Audio.Media.IS_MUSIC} != 0",
            null,
            "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC"
        )?.use{cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val durationCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val albumIdCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            while(cursor.moveToNext()){
                val id = cursor.getLong(idCol)
                Song4(
                    id = id,
                    song_title = cursor.getString(titleCol) ?: "Unknown Title",
                    song_artist = cursor.getString(artistCol) ?: "Unknown Artist",
                    song_album = cursor.getString(albumCol) ?: "Unknown Album",
                    duration = cursor.getLong(durationCol),
                    uri = ContentUris.withAppendedId(collection, id),
                    albumId = cursor.getLong(albumIdCol)
                )
            }
        }
        songs
    }
}