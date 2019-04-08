package com.mlcorrea.lostparadisefm.framework.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo.AlbumInfoVM
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.AlbumListVM
import com.mlcorrea.lostparadisefm.ui.feature.artist.ArtistListVM
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivityVM
import com.mlcorrea.lostparadisefm.ui.feature.track.TrackListVM
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

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    abstract fun bindsMainActivityVM(mainActivityVM: MainActivityVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArtistListVM::class)
    abstract fun bindsArtistListVM(artistListVM: ArtistListVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TrackListVM::class)
    abstract fun bindsTrackListVM(trackListVM: TrackListVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumInfoVM::class)
    abstract fun bindsAlbumInfoVM(albumInfoVM: AlbumInfoVM): ViewModel


}