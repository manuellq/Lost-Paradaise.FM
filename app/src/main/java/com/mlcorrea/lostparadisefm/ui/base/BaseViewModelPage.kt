package com.mlcorrea.lostparadisefm.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel on 07/04/19
 */
abstract class BaseViewModelPage<T : BaseDataSource, V : ViewModelData>(val factory: BaseDataSourceFactory<T>) :
    ViewModel() {


    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .setPageSize(30).build()

    val networkState =
        Transformations.switchMap(factory.getMutableLiveData())
        { dataSource -> dataSource.getNetworkState() }

    val listLiveData: LiveData<PagedList<ViewModelData>> = LivePagedListBuilder(factory, pagedListConfig)
        .build()

}