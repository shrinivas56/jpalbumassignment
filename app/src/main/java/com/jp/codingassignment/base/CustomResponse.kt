package com.jp.codingassignment.base

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
sealed class CustomResponse<T>{

class Success<T>(val data:T?): CustomResponse<T>()

data class Failure<T>(val throwable: Throwable): CustomResponse<T>()

}