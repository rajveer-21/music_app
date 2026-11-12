package com.example.musicapp.data

import android.content.Context
import androidx.room.*

/*------tables-------*/
@Entity(tableName = "favorite_songs")
data class FavoriteSong(
    @PrimaryKey
    val id : Long
)

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val name : String
)

@Entity(
    tableName = "playlist_songs",
    primaryKeys = ["playlistId", "songId"],
    foreignKeys = [
        ForeignKey(
            entity = PlaylistEntity::class,
            parentColumns = ["id"],
            childColumns = ["playlistId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PlaylistSong(
    val playlistId : Long,
    val songId : Long,
    val position : Int
)

/*------dao------*/
@Dao
interface LibraryDao{
    @Query("SELECT id FROM favorite_songs")
    suspend fun favoriteIds() : List<Long>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(song : FavoriteSong)
    @Query("DELETE FROM favorite_songs WHERE id = :deleteId")
    suspend fun deleteFavorite(deleteId : Long)
    @Query("SELECT *FROM playlists ORDER BY name ASC")
    suspend fun playlists() : List<PlaylistEntity>
    @Insert
    suspend fun createPlaylists(playlist : PlaylistEntity) : Long
    @Query("DELETE FROM playlists WHERE id = :id")
    suspend fun deletePlaylists(id : Long)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToPlaylist(item : PlaylistSong)
    @Query("""
        SELECT songId FROM playlist_songs 
        WHERE playlistId = :playlistId
        ORDER BY position
    """)
    suspend fun playlistSongs(playlistId : Long) : List<Long>
    @Query("""
        DELETE FROM playlist_songs 
        WHERE playlistId = :playlistId AND
        songId = :songId
    """)
    suspend fun removeFromPlaylist(playlistId : Long, songId : Long)
}

@Database(
    entities = [FavoriteSong::class, PlaylistEntity::class, PlaylistSong::class], version = 1, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase(){
    abstract fun dao(): LibraryDao
    companion object
    {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null
        fun get(context : Context): LibraryDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(context.applicationContext, LibraryDatabase::class.java, "music_library.db").build().also{INSTANCE = it}
            }
        }
    }
}


