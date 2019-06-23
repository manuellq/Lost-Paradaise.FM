package com.mlcorrea.lostparadisefm.ui.feature.track.tracks

import com.mlcorrea.domain.iteractor.track.GetTracks
import com.mlcorrea.domain.model.Track
import com.mlcorrea.lostparadisefm.ui.base.BaseViewModelPage
import com.mlcorrea.lostparadisefm.ui.feature.track.tracks.adapter.TrackDataSource
import com.mlcorrea.lostparadisefm.ui.feature.track.tracks.adapter.TrackDataSourceFactory

/**
 * Created by manuel on 07/04/19
 */
class TrackListVM  constructor(getTracks: GetTracks) :
    BaseViewModelPage<TrackDataSource, Track>(TrackDataSourceFactory(getTracks)) {


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