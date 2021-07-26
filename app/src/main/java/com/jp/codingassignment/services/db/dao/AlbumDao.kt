package com.jp.codingassignment.services.db.dao

import androidx.room.*
import com.jp.codingassignment.services.db.entity.Album

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: Album)

    @Transaction
    @Query("SELECT * FROM Album ORDER BY title ASC")
    suspend fun getAlbums(): List<Album>?
}