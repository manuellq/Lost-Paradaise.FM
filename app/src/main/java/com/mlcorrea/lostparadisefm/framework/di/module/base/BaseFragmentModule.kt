package com.mlcorrea.lostparadisefm.framework.di.module.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mlcorrea.lostparadisefm.framework.di.scope.PerFragment
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by manuel on 06/04/19
 */
@Module
class BaseFragmentModule {

    companion object {
        const val FRAGMENT = "BaseFragmentModule.fragment"
        const val CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager"
    }

    @Provides
    @Named(CHILD_FRAGMENT_MANAGER)
    @PerFragment
    internal fun childFragmentManager(@Named(FRAGMENT) fragment: Fragment): FragmentManager {
        return fragment.childFragmentManager
    }
}