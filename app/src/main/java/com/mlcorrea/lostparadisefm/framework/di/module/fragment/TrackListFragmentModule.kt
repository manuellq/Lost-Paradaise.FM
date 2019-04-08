package com.mlcorrea.lostparadisefm.framework.di.module.fragment

import androidx.fragment.app.Fragment
import com.mlcorrea.lostparadisefm.framework.di.module.base.BaseFragmentModule
import com.mlcorrea.lostparadisefm.framework.di.scope.PerFragment
import com.mlcorrea.lostparadisefm.ui.feature.track.tracks.TrackListFragment
import dagger.Binds
import dagger.Module

/**
 * Created by manuel on 07/04/19
 */
@Module(includes = [BaseFragmentModule::class])
abstract class TrackListFragmentModule {

    @Binds
    @PerFragment
    internal abstract fun fragment(trackListFragment: TrackListFragment): Fragment
}