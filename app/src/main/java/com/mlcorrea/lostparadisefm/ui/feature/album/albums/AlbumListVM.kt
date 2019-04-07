package com.mlcorrea.lostparadisefm.ui.feature.album.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mlcorrea.domain.iteractor.album.GetAlbums
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.lostparadisefm.ui.feature.album.albums.adapter.AlbumDataSourceFactory
import javax.inject.Inject


/**
 * Created by manuel on 06/04/19
 */
class AlbumListVM @Inject constructor(getAlbums: GetAlbums) :
    ViewModel() {

    private val albumSourceFactory: AlbumDataSourceFactory = AlbumDataSourceFactory(getAlbums)


    val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .setPageSize(30).build()

    val networkState =
        Transformations.switchMap(albumSourceFactory.getMutableLiveData())
        { dataSource -> dataSource.getNetworkState() }

    val listLiveData: LiveData<PagedList<ViewModelData>> = LivePagedListBuilder(albumSourceFactory, pagedListConfig)
        .build()


    override fun onCleared() {
        albumSourceFactory.getMutableLiveData().value?.disposeAll()
        super.onCleared()
    }

    fun initVM() {
//        albumSourceFactory.setQuery("believe")
//        listLiveData.value?.dataSource?.invalidate()
    }

    fun retry() {
        albumSourceFactory.getMutableLiveData().value?.retry()
    }

    fun setQuery(text: String) {
        albumSourceFactory.setQuery(text)
        listLiveData.value?.dataSource?.invalidate()
    }
}