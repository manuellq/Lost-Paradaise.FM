package com.mlcorrea.lostparadisefm.ui.feature.album.albums

import com.mlcorrea.domain.iteractor.album.GetAlbums
import com.mlcorrea.domain.model.Album
import com.mlcorrea.lostparadisefm.ui.base.BaseViewModelPage
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter.AlbumDataSource
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter.AlbumDataSourceFactory
import javax.inject.Inject


/**
 * Created by manuel on 06/04/19
 */
class AlbumListVM @Inject constructor(getAlbums: GetAlbums) :
    BaseViewModelPage<AlbumDataSource, Album>(AlbumDataSourceFactory(getAlbums)) {


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