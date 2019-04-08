package com.mlcorrea.lostparadisefm.framework.di.module.fragment

import androidx.fragment.app.Fragment
import com.mlcorrea.lostparadisefm.framework.di.module.base.BaseFragmentModule
import com.mlcorrea.lostparadisefm.framework.di.scope.PerFragment
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.AlbumListFragment
import dagger.Binds
import dagger.Module

/**
 * Created by manuel on 06/04/19
 */
@Module(includes = [BaseFragmentModule::class])
abstract class AlbumListFragmentModule {

    @Binds
    @PerFragment
    internal abstract fun fragment(albumListFragment: AlbumListFragment): Fragment
}