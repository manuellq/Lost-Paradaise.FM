package com.mlcorrea.lostparadisefm.framework.di.module.activity

import androidx.appcompat.app.AppCompatActivity
import com.mlcorrea.lostparadisefm.framework.di.module.base.BaseActivityModule
import com.mlcorrea.lostparadisefm.framework.di.scope.PerActivity
import com.mlcorrea.lostparadisefm.ui.feature.track.trackinfo.TrackInfoActivity
import dagger.Binds
import dagger.Module

/**
 * Created by manuel on 08/04/19
 */
@Module(includes = [BaseActivityModule::class])
abstract class TrackInfoActivityModule {


    @Binds
    @PerActivity
    internal abstract fun provideTrackInfoActivity(trackInfoActivity: TrackInfoActivity): AppCompatActivity

}