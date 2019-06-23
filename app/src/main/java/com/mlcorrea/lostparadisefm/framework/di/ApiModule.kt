package com.mlcorrea.lostparadisefm.framework.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.mlcorrea.domain.exception.HttpNoInternetConnectionException
import com.mlcorrea.lostparadisefm.BuildConfig
import com.mlcorrea.lostparadisefm.framework.network.NetworkController
import com.mlcorrea.lostparadisefm.framework.network.NetworkControllerImpl
import com.mlcorrea.lostparadisefm.framework.retrofit.apimanager.ApiManager
import com.mlcorrea.lostparadisefm.framework.retrofit.apimanager.ApiManagerImpl
import com.mlcorrea.lostparadisefm.framework.retrofit.helper.ApiServiceHelperController
import com.mlcorrea.lostparadisefm.framework.retrofit.helper.ApiServiceHelperControllerImpl
import com.mlcorrea.lostparadisefm.util.UtilsSecurityNetwork
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by manuel on 22/06/19
 */

private const val HTTP_LOGIN_INTERCEPTOR_QUALIFIER = "httpLoggingInterceptor"
private const val STETHO_INTERCEPTOR_QUALIFIER = "stethoInterceptor"
private const val KEY_API_INTERCEPTOR_QUALIFIER = "AddApiKeyToHeader"
private const val CONNECTIVITY_INTERCEPTOR_QUALIFIER = "NoConnectivityConnection"
private const val API_URL_QUALIFIER = "api_url"
private const val API_KEY_QUALIFIER = "api_key"

val apiModule = module {

    single<ApiManager> { ApiManagerImpl(get()) }

    factory<ApiServiceHelperController> { ApiServiceHelperControllerImpl(get(named(API_URL_QUALIFIER)), get()) }

    single {
        UtilsSecurityNetwork
            .getOkHttpClient(
                get(named(STETHO_INTERCEPTOR_QUALIFIER)),
                get(named(HTTP_LOGIN_INTERCEPTOR_QUALIFIER)),
                get(named(CONNECTIVITY_INTERCEPTOR_QUALIFIER)),
                get(named(KEY_API_INTERCEPTOR_QUALIFIER)),
                get(named(IS_DEBUG_QUALIFIER))
            )
    }

    factory(named(API_URL_QUALIFIER)) { BuildConfig.BASE_URL }

    factory(named(API_KEY_QUALIFIER)) { BuildConfig.API_KEY }

    single(named(HTTP_LOGIN_INTERCEPTOR_QUALIFIER)) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (get(named(IS_DEBUG_QUALIFIER))) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        httpLoggingInterceptor
    }

    single(named(STETHO_INTERCEPTOR_QUALIFIER)) { StethoInterceptor() }

    single<NetworkController> { NetworkControllerImpl(get()) } bind NetworkController::class

    factory(named(CONNECTIVITY_INTERCEPTOR_QUALIFIER)) {
        Interceptor { chain: Interceptor.Chain ->
            if (!get<NetworkController>().isConnected) {
                throw HttpNoInternetConnectionException()
            }
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
    }

    factory(named(KEY_API_INTERCEPTOR_QUALIFIER)) {
        Interceptor { chain: Interceptor.Chain ->
            val url = chain.request().url().newBuilder()
                .addQueryParameter("api_key", get(named(API_KEY_QUALIFIER)))
                .addQueryParameter("format", "json")
                .build()

            chain.proceed(chain.request().newBuilder().url(url).build())
        }
    }

}