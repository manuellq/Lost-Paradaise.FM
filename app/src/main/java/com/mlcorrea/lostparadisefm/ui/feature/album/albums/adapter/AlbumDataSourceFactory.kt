package com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter

import androidx.paging.DataSource
import com.mlcorrea.domain.iteractor.album.GetAlbums
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.lostparadisefm.ui.base.BaseDataSourceFactory


/**
 * Created by manuel.correa on 14/03/2018.
 */
class AlbumDataSourceFactory(private val getAlbums: GetAlbums) :
    BaseDataSourceFactory<AlbumDataSource>() {


    override fun create(): DataSource<Long, ViewModelData> {
        val transactionsDataSource = AlbumDataSource(getQuery(), getAlbums)
        getMutableLiveData().postValue(transactionsDataSource)
        return transactionsDataSource
    }

}