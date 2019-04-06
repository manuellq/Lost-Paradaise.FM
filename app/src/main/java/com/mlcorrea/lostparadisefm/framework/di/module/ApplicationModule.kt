package com.mlcorrea.lostparadisefm.framework.di.module

import android.content.Context
import com.mlcorrea.data.executor.JobExecutor
import com.mlcorrea.domain.executor.PostExecutionThread
import com.mlcorrea.domain.executor.ThreadExecutor
import com.mlcorrea.lostparadisefm.BuildConfig
import com.mlcorrea.lostparadisefm.ui.App
import com.mlcorrea.lostparadisefm.ui.UIThread
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by manuel on 06/04/19
 */
@Module(includes = [AndroidInjectionModule::class])
internal class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Named("isDebugMode")
    internal fun provideDebugMode(): Boolean {
        return BuildConfig.DEBUG
    }
}