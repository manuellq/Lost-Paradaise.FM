package com.mlcorrea.lostparadisefm.ui

import android.app.Activity
import android.app.Application
import android.os.StrictMode
import com.facebook.stetho.Stetho
import com.mlcorrea.lostparadisefm.BuildConfig
import com.mlcorrea.lostparadisefm.framework.di.components.ApplicationComponent
import com.mlcorrea.lostparadisefm.framework.di.components.DaggerApplicationComponent
import com.mlcorrea.lostparadisefm.framework.timber.LifeTreeTimber
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by manuel on 06/04/19
 */
class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        initTimber()
        strictMode()
        initializeStetho()
        injectApplicationComponent()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return this.activityInjector
    }

    private fun injectApplicationComponent() {
        this.applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        this.applicationComponent.inject(this)
    }

    /**
     * We log everything in debug mode and we plant a tree for Release
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            val formatElementPlant = "%s:%s"
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        formatElementPlant, super.createStackElementTag(element),
                        element.lineNumber
                    )
                }
            })
        } else {
            Timber.plant(LifeTreeTimber())
        }
    }

    /**
     * This is only used in debug mode
     */
    private fun initializeStetho() {
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        )
    }

    /**
     * this is only for testing purposes and it only should be used in debug mode, it check ARN error
     * and others things
     */
    private fun strictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
        }
    }
}