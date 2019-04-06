package com.mlcorrea.lostparadisefm.framework.retrofit.helper

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

/**
 * Created by manuel on 06/04/19
 */
interface RetrofitUtils {

    fun getBody(response: Response<*>): ResponseBody?

    fun getCode(response: Response<*>): Int

    fun <T> getResponse(result: Result<T>): Response<T>?

    fun <T> resultIsError(result: Result<T>): Boolean

    fun <T> resultGetError(result: Result<T>): Throwable?
}