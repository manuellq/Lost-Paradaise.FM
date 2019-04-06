package com.mlcorrea.lostparadisefm.framework.retrofit.apimanager

import com.mlcorrea.lostparadisefm.framework.retrofit.helper.ApiServiceHelperController
import com.mlcorrea.lostparadisefm.framework.retrofit.service.ApiService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by manuel on 06/04/19
 */
@Singleton
class ApiManagerImpl @Inject constructor(
    private val apiServiceHelperController: ApiServiceHelperController
) :
    ApiManager {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService

    init {
        createService()
    }

    override val apiServices: ApiService get() = apiService

    /*--------------PRIVATE METHOD------------*/

    private fun createService() {
        retrofit = apiServiceHelperController.createRetrofit()

        apiService = apiServiceHelperController.createService(retrofit)
    }
}