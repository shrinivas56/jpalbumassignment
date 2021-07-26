package com.jp.codingassignment.services.remote


import android.content.Context
import com.jp.codingassignment.services.db.entity.Album
import com.jp.codingassignment.utils.ConnectivityInterceptor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Shrinivas Pai on 27/07/21.
 */
class ApiManager(mContext: Context) : ApiInterface {

    private val apiBasePath = "https://jsonplaceholder.typicode.com"
    private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient: OkHttpClient.Builder

    init {
        okHttpClient = getHttpClientBuilder(mContext)
    }
    private fun getApiInterface(): ApiInterface {
        val builder = okHttpClient
        val retrofit = Retrofit.Builder()
            .baseUrl(apiBasePath)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

    private fun getHttpClientBuilder(mContext: Context): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()

        val protoList = ArrayList<Protocol>()
        protoList.add(Protocol.HTTP_2)
        protoList.add(Protocol.HTTP_1_1)
        builder.protocols(protoList)
        //validate connection pool
        builder.connectionPool(ConnectionPool(0, 1, TimeUnit.MILLISECONDS))
        builder.retryOnConnectionFailure(true)
        builder.addInterceptor(httpLoggingInterceptor)
        builder.addInterceptor(ConnectivityInterceptor(mContext))
        val timeout = 30
        builder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
        builder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        builder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)

        return builder
    }

    override suspend fun getAlbums(): List<Album> = getApiInterface().getAlbums()

}