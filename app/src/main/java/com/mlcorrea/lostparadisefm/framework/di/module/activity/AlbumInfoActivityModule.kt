package com.mlcorrea.lostparadisefm.framework.di.module.activity

import androidx.appcompat.app.AppCompatActivity
import com.mlcorrea.lostparadisefm.framework.di.module.base.BaseActivityModule
import com.mlcorrea.lostparadisefm.framework.di.scope.PerActivity
import com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo.AlbumInfoActivity
import dagger.Binds
import dagger.Module

/**
 * Created by manuel on 08/04/19
 */
@Module(includes = [BaseActivityModule::class])
abstract class AlbumInfoActivityModule {

    @Binds
    @PerActivity
    internal abstract fun provideAlbumInfoActivity(albumInfoActivity: AlbumInfoActivity): AppCompatActivity
}