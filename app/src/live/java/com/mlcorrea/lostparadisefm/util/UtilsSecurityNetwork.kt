package com.mlcorrea.lostparadisefm.util

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by manuel on 06/04/19
 */
object UtilsSecurityNetwork {

    fun getOkHttpClient(
        stethoInterceptor: StethoInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        connectivityInterceptor: Interceptor,
        headerInterceptor: Interceptor,
        debugMode: Boolean
    ): OkHttpClient {
        val clientBuilder = okhttp3.OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)//this is for retrofit to log all the request;
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(headerInterceptor)

        if (debugMode) {
            clientBuilder
                .addNetworkInterceptor(stethoInterceptor)//this is to intercept network connection
        }

        return clientBuilder.build()
    }
}