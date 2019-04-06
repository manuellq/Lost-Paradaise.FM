package com.mlcorrea.lostparadisefm.framework.di.components

import com.mlcorrea.lostparadisefm.framework.di.module.ActivityModule
import com.mlcorrea.lostparadisefm.framework.di.module.ApiModule
import com.mlcorrea.lostparadisefm.framework.di.module.ApplicationModule
import com.mlcorrea.lostparadisefm.framework.di.module.BuildersModule
import com.mlcorrea.lostparadisefm.framework.di.viewmodel.ViewModelModule
import com.mlcorrea.lostparadisefm.ui.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by manuel on 06/04/19
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(
    modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, ActivityModule::class,
        ApiModule::class, BuildersModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): ApplicationComponent
    }
}