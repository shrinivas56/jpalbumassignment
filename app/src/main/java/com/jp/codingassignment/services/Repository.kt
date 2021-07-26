package com.jp.codingassignment.services

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jp.codingassignment.base.CustomResponse
import com.jp.codingassignment.services.db.entity.Album
import com.jp.codingassignment.services.remote.ApiManager
import com.jp.codingassignment.services.remote.AppDataManager
import com.jp.codingassignment.services.remote.DataManager
import com.jp.codingassignment.services.remote.DbManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
//class Repository used as a mediator between ui and data layers
class Repository(application: Application) {

    private val TAG: String = javaClass.simpleName
    private val dataManager: DataManager
    var onNetworkError = MutableLiveData<String>()


    init {
        dataManager = AppDataManager(application.applicationContext, ApiManager(application.applicationContext), DbManager.getDatabase(application.applicationContext))
    }


    suspend fun getAlbumData(): List<Album>? {

        val response = callApi{
                dataManager.getAlbums()
            }
        when(response){
                is CustomResponse.Success->{
                    withContext(Dispatchers.IO) {
                        response?.data?.let {
                            dataManager.saveAlbum(response.data)
                        }
                    }
                }
        }
        var items = dataManager.getAlbumFromDb()
        if((items==null || items.isEmpty()) && response is CustomResponse.Failure){
            onNetworkError.value = response.throwable.message
        }
        return  items
    }

    //A common layer for all the Apis which can handle different exceptions if required
    private suspend fun <T> callApi(apiCall: suspend ()->T): CustomResponse<T> {

        return try {
            // Call api and if every thing goes well
            // the success response will be given

            CustomResponse.Success(apiCall.invoke())
        } catch (throwable: Exception) {

            when (throwable) {
                is SocketTimeoutException, is SSLHandshakeException, is UnknownHostException -> {
                   // To Handle server errors differently
                }
                else -> {

                }
            }
            return CustomResponse.Failure(throwable)
        }
    }
}