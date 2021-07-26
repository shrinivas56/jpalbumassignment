package com.jp.codingassignment.base

import android.app.Application
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import com.jp.codingassignment.services.Repository

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application){
    protected val repository: Repository = Repository(application)

    var isLoading: ObservableInt = ObservableInt(View.VISIBLE)
    val onNetworkError = repository.onNetworkError
}