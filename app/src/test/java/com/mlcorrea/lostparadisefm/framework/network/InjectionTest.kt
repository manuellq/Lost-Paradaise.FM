package com.mlcorrea.lostparadisefm.framework.network

import android.content.Context
import com.google.common.truth.Truth
import com.mlcorrea.data.repository.PlatformRepositoryImpl
import com.mlcorrea.lostparadisefm.UnitTest
import com.mlcorrea.lostparadisefm.framework.retrofit.apimanager.ApiManager
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

/**
 * Created by manuel on 08/04/19
 */
class InjectionTest : UnitTest() {


    @Before
    fun setUp() {
    }

    @Test
    fun providePlatformRepositoryImpl() {
        val apiManager = mockk<ApiManager>()
        val context = mockk<Context>()
        val result = Injection.providePlatformRepositoryImpl(context, apiManager)

        Truth.assertThat(result).isInstanceOf(PlatformRepositoryImpl::class.java)
    }
}