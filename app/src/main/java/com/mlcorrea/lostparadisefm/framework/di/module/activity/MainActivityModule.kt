package com.mlcorrea.lostparadisefm.framework.di.module.activity

import androidx.appcompat.app.AppCompatActivity
import com.mlcorrea.lostparadisefm.framework.di.module.base.BaseActivityModule
import com.mlcorrea.lostparadisefm.framework.di.module.fragment.AlbumListFragmentModule
import com.mlcorrea.lostparadisefm.framework.di.module.fragment.ArtistsFragmentModule
import com.mlcorrea.lostparadisefm.framework.di.module.fragment.TrackListFragmentModule
import com.mlcorrea.lostparadisefm.framework.di.scope.PerActivity
import com.mlcorrea.lostparadisefm.framework.di.scope.PerFragment
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.AlbumListFragment
import com.mlcorrea.lostparadisefm.ui.feature.artist.artists.ArtistsFragment
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivity
import com.mlcorrea.lostparadisefm.ui.feature.track.tracks.TrackListFragment
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

    @PerFragment
    @ContributesAndroidInjector(modules = [ArtistsFragmentModule::class])
    internal abstract fun artistsFragment(): ArtistsFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [TrackListFragmentModule::class])
    internal abstract fun trackListFragmen(): TrackListFragment

}