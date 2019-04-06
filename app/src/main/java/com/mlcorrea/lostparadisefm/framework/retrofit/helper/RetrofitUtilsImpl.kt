package com.mlcorrea.lostparadisefm.framework.retrofit.helper

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result
import javax.inject.Inject

/**
 * Created by manuel on 06/04/19
 */
class RetrofitUtilsImpl @Inject constructor() : RetrofitUtils {

    override fun getBody(response: Response<*>): ResponseBody? {
        return response.errorBody()
    }

    override fun getCode(response: Response<*>): Int {
        return response.code()
    }

    override fun <T> getResponse(result: Result<T>): Response<T>? {
        return result.response()
    }

    override fun <T> resultIsError(result: Result<T>): Boolean {
        return result.isError
    }

    override fun <T> resultGetError(result: Result<T>): Throwable? {
        return result.error()
    }
}