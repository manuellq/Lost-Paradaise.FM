package com.mlcorrea.lostparadisefm.framework.retrofit.helper

import com.mlcorrea.lostparadisefm.framework.retrofit.service.ApiService
import retrofit2.Retrofit

/**
 * Created by manuel on 06/04/19
 */
interface ApiServiceHelperController {

    fun createRetrofit(): Retrofit

    fun createService(retrofit: Retrofit): ApiService
}