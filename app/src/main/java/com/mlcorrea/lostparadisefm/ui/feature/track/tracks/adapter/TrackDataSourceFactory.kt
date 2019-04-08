package com.mlcorrea.lostparadisefm.ui.feature.track.tracks.adapter

import androidx.paging.DataSource
import com.mlcorrea.domain.iteractor.track.GetTracks
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.lostparadisefm.ui.base.BaseDataSourceFactory


/**
 * Created by manuel.correa on 14/03/2018.
 */
class TrackDataSourceFactory(private val getTracks: GetTracks) :
    BaseDataSourceFactory<TrackDataSource>() {


    override fun create(): DataSource<Long, ViewModelData> {
        val transactionsDataSource = TrackDataSource(getQuery(), getTracks)
        getMutableLiveData().postValue(transactionsDataSource)
        return transactionsDataSource
    }

}