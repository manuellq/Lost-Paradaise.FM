package com.mlcorrea.lostparadisefm.framework.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.mlcorrea.domain.exception.HttpNoInternetConnectionException
import com.mlcorrea.domain.repository.PlatformRepository
import com.mlcorrea.lostparadisefm.BuildConfig
import com.mlcorrea.lostparadisefm.framework.network.Injection
import com.mlcorrea.lostparadisefm.framework.network.NetworkController
import com.mlcorrea.lostparadisefm.framework.network.NetworkControllerImpl
import com.mlcorrea.lostparadisefm.framework.retrofit.apimanager.ApiManager
import com.mlcorrea.lostparadisefm.framework.retrofit.apimanager.ApiManagerImpl
import com.mlcorrea.lostparadisefm.framework.retrofit.helper.ApiServiceHelperController
import com.mlcorrea.lostparadisefm.framework.retrofit.helper.ApiServiceHelperControllerImpl
import com.mlcorrea.lostparadisefm.ui.App
import com.mlcorrea.lostparadisefm.util.UtilsSecurityNetwork
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by manuel on 06/04/19
 */
@Module
internal class ApiModule {

    @Singleton
    @Provides
    fun provideApiManager(apiManager: ApiManagerImpl): ApiManager {
        return apiManager
    }

    @Provides
    fun provideApiServiceHelperController(apiServiceHelperController: ApiServiceHelperControllerImpl): ApiServiceHelperController {
        return apiServiceHelperController
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        stethoInterceptor: StethoInterceptor,
        @Named("NoConnectivityConnectionInterceptor") connectivityInterceptor: Interceptor,
        @Named("AddApiKeyToHeader") headerInterceptor: Interceptor,
        @Named("isDebugMode") debugMode: Boolean
    ): OkHttpClient {
        return UtilsSecurityNetwork
            .getOkHttpClient(
                stethoInterceptor,
                httpLoggingInterceptor,
                connectivityInterceptor,
                headerInterceptor,
                debugMode
            )
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptorRetrofit(@Named("isDebugMode") debugMode: Boolean): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (debugMode) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun stethoInterceptorProvider(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Named("apiKey")
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    fun provideNetworkHandler(app: App): NetworkController {
        return NetworkControllerImpl(app)
    }

    @Provides
    @Named("NoConnectivityConnectionInterceptor")
    fun isConnectedInterceptor(networkHandler: NetworkController): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            if (!networkHandler.isConnected) {
                throw HttpNoInternetConnectionException()
            }
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
    }

    @Provides
    @Named("AddApiKeyToHeader")
    fun extraHeaderParamInterceptor(@Named("apiKey") apiKey: String): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val url = chain.request().url().newBuilder()
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("format", "json")
                .build()

            chain.proceed(chain.request().newBuilder().url(url).build())
        }
    }

    @Provides
    @Singleton
    fun providePlatformRepository(apiManager: ApiManager): PlatformRepository {
        return Injection.providePlatformRepositoryImpl(apiManager)
    }
}