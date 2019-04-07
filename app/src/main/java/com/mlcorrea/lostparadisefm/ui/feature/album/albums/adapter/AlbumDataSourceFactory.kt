package com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mlcorrea.domain.iteractor.album.GetAlbums
import com.mlcorrea.domain.model.adapter.ViewModelData


/**
 * Created by manuel.correa on 14/03/2018.
 */
class AlbumDataSourceFactory(private val getAlbums: GetAlbums) :
    DataSource.Factory<Long, ViewModelData>() {

    private val mutableLiveData = MutableLiveData<AlbumDataSource>()
    private var query: String = ""

    override fun create(): DataSource<Long, ViewModelData> {
        val transactionsDataSource = AlbumDataSource(query, getAlbums)
        mutableLiveData.postValue(transactionsDataSource)
        return transactionsDataSource
    }

    fun getMutableLiveData(): MutableLiveData<AlbumDataSource> {
        return mutableLiveData
    }

    fun setQuery(text: String) {
        query = text
    }
}