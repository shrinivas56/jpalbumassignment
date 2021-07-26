package com.jp.codingassignment.ui.album.listing

import android.app.Application
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jp.codingassignment.base.BaseViewModel
import com.jp.codingassignment.services.db.entity.Album
import kotlinx.coroutines.launch

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
class AlbumListingViewModel(application: Application) : BaseViewModel(application) {

    private val TAG = javaClass.simpleName

    val albumsLiveData = MutableLiveData<List<Album>?>()

    val isDataAvailable = ObservableInt(View.GONE)

    fun getData() {

        viewModelScope.launch {

            isLoading.set(View.VISIBLE)

            var items: List<Album>? = repository.getAlbumData()

            isLoading.set(View.GONE)
            albumsLiveData.postValue(items)
        }
    }
}