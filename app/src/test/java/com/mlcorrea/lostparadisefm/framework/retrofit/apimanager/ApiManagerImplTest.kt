package com.mlcorrea.lostparadisefm.framework.retrofit.apimanager

import com.google.common.truth.Truth
import com.mlcorrea.lostparadisefm.UnitTest
import com.mlcorrea.lostparadisefm.framework.retrofit.helper.ApiServiceHelperController
import com.mlcorrea.lostparadisefm.framework.retrofit.service.ApiService
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

/**
 * Created by manuel on 08/04/19
 */
class ApiManagerImplTest : UnitTest() {

    @MockK
    lateinit var apiServiceHelperController: ApiServiceHelperController


    @Before
    fun setUp() {

    }

    @Test
    fun `Check if the app init the API services`() {
        //Given
        val retrofit = mockk<Retrofit>()
        val apiService = mockk<ApiService>()
        every { apiServiceHelperController.createRetrofit() }.returns(retrofit)
        every { apiServiceHelperController.createService(retrofit) }.returns(apiService)
        val apiManager = ApiManagerImpl(apiServiceHelperController)
        //When
        val resultApiService = apiManager.apiServices
        //Then
        Truth.assertThat(resultApiService).isEqualTo(apiService)
    }

}