package com.mlcorrea.lostparadisefm.framework.di.module

import com.mlcorrea.lostparadisefm.framework.di.module.activity.AlbumInfoActivityModule
import com.mlcorrea.lostparadisefm.framework.di.module.activity.ArtistInfoActivityModule
import com.mlcorrea.lostparadisefm.framework.di.module.activity.MainActivityModule
import com.mlcorrea.lostparadisefm.framework.di.module.activity.TrackInfoActivityModule
import com.mlcorrea.lostparadisefm.framework.di.scope.PerActivity
import com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo.AlbumInfoActivity
import com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo.ArtistInfoActivity
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivity
import com.mlcorrea.lostparadisefm.ui.feature.track.trackinfo.TrackInfoActivity
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

    @PerActivity
    @ContributesAndroidInjector(modules = [AlbumInfoActivityModule::class])
    internal abstract fun albumInfoActivity(): AlbumInfoActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [ArtistInfoActivityModule::class])
    internal abstract fun artistInfoActivity(): ArtistInfoActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [TrackInfoActivityModule::class])
    internal abstract fun trackInfoActivity(): TrackInfoActivity


}