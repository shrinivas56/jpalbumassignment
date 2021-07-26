package com.jp.codingassignment.services.remote

import com.jp.codingassignment.services.db.entity.Album
import retrofit2.http.GET

/**
* Created by Shrinivas Pai on 23/07/21.
*/
interface ApiInterface {

    @GET("/albums")
    suspend fun getAlbums(): List<Album>


}