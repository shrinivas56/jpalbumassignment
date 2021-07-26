package com.jp.codingassignment.services.remote

import android.content.Context
import com.jp.codingassignment.services.db.entity.Album


/**
 * Created by Shrinivas Pai on 23/07/21.
 */
class AppDataManager(val context: Context, private val apiHelper: ApiInterface, private val dbHelper: DbInterface) : DataManager {

    override suspend fun getAlbums(): List<Album> =apiHelper.getAlbums()
    override suspend fun saveAlbum(albums: List<Album>) = dbHelper.saveAlbum(albums)
    override suspend fun getAlbumFromDb(): List<Album>? = dbHelper.getAlbumFromDb()

}