package com.jp.codingassignment.utils

import java.io.IOException

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
class NoNetworkException : IOException() {

    override val message: String?
        get() = "No internet connectivity, Please try again later."
}