package com.jp.codingassignment.services.remote

import com.jp.codingassignment.services.db.entity.Album

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
interface DbInterface {

    suspend fun saveAlbum(albums: List<Album>)

    suspend fun getAlbumFromDb(): List<Album>?
}