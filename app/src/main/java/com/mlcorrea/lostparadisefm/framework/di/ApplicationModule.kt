package com.mlcorrea.lostparadisefm.framework.di

import com.mlcorrea.data.executor.JobExecutor
import com.mlcorrea.domain.executor.PostExecutionThread
import com.mlcorrea.domain.executor.ThreadExecutor
import com.mlcorrea.domain.iteractor.album.GetAlbumInfo
import com.mlcorrea.domain.iteractor.album.GetAlbums
import com.mlcorrea.domain.iteractor.artist.GetArtist
import com.mlcorrea.domain.iteractor.artist.GetArtistInfo
import com.mlcorrea.domain.iteractor.track.GetTrackInfo
import com.mlcorrea.domain.iteractor.track.GetTracks
import com.mlcorrea.lostparadisefm.BuildConfig
import com.mlcorrea.lostparadisefm.framework.network.Injection
import com.mlcorrea.lostparadisefm.ui.UIThread
import com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo.AlbumInfoActivity
import com.mlcorrea.lostparadisefm.ui.feature.album.albuminfo.AlbumInfoVM
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.AlbumListFragment
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.AlbumListVM
import com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo.ArtistInfoActivity
import com.mlcorrea.lostparadisefm.ui.feature.artist.artistinfo.ArtistInfoVM
import com.mlcorrea.lostparadisefm.ui.feature.artist.artists.ArtistListVM
import com.mlcorrea.lostparadisefm.ui.feature.artist.artists.ArtistsFragment
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivity
import com.mlcorrea.lostparadisefm.ui.feature.home.MainActivityVM
import com.mlcorrea.lostparadisefm.ui.feature.track.trackinfo.TrackInfoActivity
import com.mlcorrea.lostparadisefm.ui.feature.track.trackinfo.TrackInfoVM
import com.mlcorrea.lostparadisefm.ui.feature.track.tracks.TrackListFragment
import com.mlcorrea.lostparadisefm.ui.feature.track.tracks.TrackListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by manuel on 22/06/19
 */
const val IS_DEBUG_QUALIFIER = "isDebug"

val appModule = module {

    single {
        Injection.providePlatformRepositoryImpl(get(), get())
    }

}

val dataModule = module {

    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UIThread() }
    factory(named(IS_DEBUG_QUALIFIER)) { BuildConfig.DEBUG }

}

val activityScope = module {

    scope(named<AlbumInfoActivity>()) {
        scoped { GetAlbumInfo(get(), get(), get()) }
        viewModel { AlbumInfoVM(get()) }
    }

    scope(named<ArtistInfoActivity>()) {
        scoped { GetArtistInfo(get(), get(), get()) }
        viewModel { ArtistInfoVM(get()) }
    }

    scope(named<MainActivity>()) {
        viewModel { MainActivityVM() }
    }

    scope(named<TrackInfoActivity>()) {
        scoped { GetTrackInfo(get(), get(), get()) }
        viewModel { TrackInfoVM(get()) }
    }
}

val fragmentScope = module {

    scope(named<AlbumListFragment>()) {
        scoped { GetAlbums(get(), get(), get()) }
        viewModel { AlbumListVM(get()) }
    }

    scope(named<ArtistsFragment>()) {
        scoped { GetArtist(get(), get(), get()) }
        viewModel { ArtistListVM(get()) }
    }

    scope(named<TrackListFragment>()) {
        scoped { GetTracks(get(), get(), get()) }
        viewModel { TrackListVM(get()) }
    }
}