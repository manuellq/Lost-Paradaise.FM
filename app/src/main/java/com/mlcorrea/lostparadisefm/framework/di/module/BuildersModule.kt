package com.mlcorrea.lostparadisefm.framework.di.module

import com.mlcorrea.lostparadisefm.framework.di.module.activity.MainActivityModule
import com.mlcorrea.lostparadisefm.framework.di.scope.PerActivity
import com.mlcorrea.lostparadisefm.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by manuel on 06/04/19
 */
@Module
abstract class BuildersModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivity(): MainActivity
}