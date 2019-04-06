package com.mlcorrea.lostparadisefm.framework.di.module.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.mlcorrea.lostparadisefm.framework.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by manuel on 06/04/19
 */
@Module
class BaseActivityModule {

    companion object {
        const val ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager"
    }

    @Provides
    @Named(ACTIVITY_FRAGMENT_MANAGER)
    @PerActivity
    fun activityFragmentManager(activity: AppCompatActivity): FragmentManager {
        return activity.supportFragmentManager
    }
}