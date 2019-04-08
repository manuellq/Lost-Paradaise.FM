package com.mlcorrea.lostparadisefm.ui.feature.artist.artists

import com.mlcorrea.domain.iteractor.artist.GetArtist
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.lostparadisefm.ui.base.BaseViewModelPage
import com.mlcorrea.lostparadisefm.ui.feature.artist.artists.adapter.ArtistDataSource
import com.mlcorrea.lostparadisefm.ui.feature.artist.artists.adapter.ArtistDataSourceFactory
import javax.inject.Inject

/**
 * Created by manuel on 07/04/19
 */
class ArtistListVM @Inject constructor(getArtist: GetArtist) :
    BaseViewModelPage<ArtistDataSource, Artist>(ArtistDataSourceFactory(getArtist)) {


    override fun onCleared() {
        factory.getMutableLiveData().value?.disposeAll()
        super.onCleared()
    }

    fun retry() {
        factory.getMutableLiveData().value?.retry()
    }

    fun setQuery(text: String) {
        factory.setQuery(text)
        listLiveData.value?.dataSource?.invalidate()
    }
}