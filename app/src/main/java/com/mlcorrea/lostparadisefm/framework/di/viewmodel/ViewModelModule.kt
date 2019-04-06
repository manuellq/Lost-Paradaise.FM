package com.mlcorrea.lostparadisefm.framework.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mlcorrea.lostparadisefm.ui.album.AlbumListVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by manuel on 06/04/19
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AlbumListVM::class)
    abstract fun bindsAlbumListVM(albumListVM: AlbumListVM): ViewModel

}