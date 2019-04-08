package com.mlcorrea.domain.network

import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel on 07/04/19
 */
enum class StatusRequest {
    INIT,
    RUNNING,
    SUCCESS,
    FAILED,
    EMPTY
}

data class NetworkRequestState(
    val status: StatusRequest,
    val exception: Throwable? = null
) : ViewModelData {

    companion object {
        val INIT = NetworkRequestState(StatusRequest.INIT)
        val LOADED = NetworkRequestState(StatusRequest.SUCCESS)
        val LOADING = NetworkRequestState(StatusRequest.RUNNING)
        val EMPTY = NetworkRequestState(StatusRequest.EMPTY)
        fun error(exception: Throwable?) = NetworkRequestState(StatusRequest.FAILED, exception)
    }
}