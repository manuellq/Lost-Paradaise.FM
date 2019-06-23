package com.mlcorrea.domain.model.response

/**
 * Created by manuel on 08/04/19
 */
sealed class ResponseRx<T> {
    class Loading<T> : ResponseRx<T>()
    class Error<T>(val exception: Exception) : ResponseRx<T>()
    class Success<T>(val data: T? = null) : ResponseRx<T>()
}