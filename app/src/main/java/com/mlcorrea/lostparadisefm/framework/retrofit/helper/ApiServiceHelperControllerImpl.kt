package com.mlcorrea.lostparadisefm.framework.retrofit.helper

import com.mlcorrea.lostparadisefm.framework.retrofit.service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by manuel on 06/04/19
 */
class ApiServiceHelperControllerImpl @Inject constructor(
    @Named("apiUrl") private val baseUrl: String, private val provideOkHttpClient: OkHttpClient
) :
    ApiServiceHelperController {

    override fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideOkHttpClient)
            .build()
    }

    override fun createService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java)
    }
}