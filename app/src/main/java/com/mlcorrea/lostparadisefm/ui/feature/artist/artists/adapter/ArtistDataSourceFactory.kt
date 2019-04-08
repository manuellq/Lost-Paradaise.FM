package com.mlcorrea.lostparadisefm.ui.feature.artist.artists.adapter

import androidx.paging.DataSource
import com.mlcorrea.domain.iteractor.artist.GetArtist
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.lostparadisefm.ui.base.BaseDataSourceFactory


/**
 * Created by manuel.correa on 14/03/2018.
 */
class ArtistDataSourceFactory(private val getArtist: GetArtist) :
    BaseDataSourceFactory<ArtistDataSource>() {


    override fun create(): DataSource<Long, ViewModelData> {
        val transactionsDataSource = ArtistDataSource(getQuery(), getArtist)
        getMutableLiveData().postValue(transactionsDataSource)
        return transactionsDataSource
    }

}