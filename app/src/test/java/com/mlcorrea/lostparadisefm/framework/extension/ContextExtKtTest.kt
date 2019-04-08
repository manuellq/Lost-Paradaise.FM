package com.mlcorrea.lostparadisefm.framework.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.common.truth.Truth
import com.mlcorrea.lostparadisefm.UnitTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

/**
 * Created by manuel on 08/04/19
 */
class ContextExtKtTest : UnitTest() {

    @Test
    fun `Test network info`() {
        val context = mockk<Context>()
        val connectivityManager = mockk<ConnectivityManager>()
        val networkInfo = mockk<NetworkInfo>()
        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) }.returns(connectivityManager)
        every { connectivityManager.activeNetworkInfo }.returns(networkInfo)
        //When
        val result = context.networkInfo
        //Then
        Truth.assertThat(result).isEqualTo(networkInfo)
    }

    @Test
    fun `Test network info when it return null`() {
        val context = mockk<Context>()
        val connectivityManager = mockk<ConnectivityManager>()
        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) }.returns(connectivityManager)
        every { connectivityManager.activeNetworkInfo }.returns(null)
        //When
        val result = context.networkInfo
        //Then
        Truth.assertThat(result).isNull()
    }

    @Test(expected = Exception::class)
    fun `Test network info when there is a exception`() {
        val context = mockk<Context>()
        every { context.networkInfo }.throws(Exception())
        //When
        context.networkInfo
    }
}