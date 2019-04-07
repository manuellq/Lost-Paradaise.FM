package com.mlcorrea.lostparadisefm.framework.di.module.activity

import androidx.appcompat.app.AppCompatActivity
import com.mlcorrea.lostparadisefm.framework.di.module.base.BaseActivityModule
import com.mlcorrea.lostparadisefm.framework.di.module.fragment.AlbumListFragmentModule
import com.mlcorrea.lostparadisefm.framework.di.scope.PerActivity
import com.mlcorrea.lostparadisefm.framework.di.scope.PerFragment
import com.mlcorrea.lostparadisefm.ui.album.albums.AlbumListFragment
import com.mlcorrea.lostparadisefm.ui.home.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by manuel on 06/04/19
 */
@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {

    @Binds
    @PerActivity
    internal abstract fun provideMainActivity(mainActivity: MainActivity): AppCompatActivity

    @PerFragment
    @ContributesAndroidInjector(modules = [AlbumListFragmentModule::class])
    internal abstract fun albumListFragment(): AlbumListFragment
}