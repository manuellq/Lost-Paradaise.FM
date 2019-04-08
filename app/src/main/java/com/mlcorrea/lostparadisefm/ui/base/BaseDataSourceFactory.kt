package com.mlcorrea.lostparadisefm.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel on 07/04/19
 */
abstract class BaseDataSourceFactory<T : BaseDataSource> :
    DataSource.Factory<Long, ViewModelData>() {

    private val mutableLiveData = MutableLiveData<T>()
    private var query: String = ""

    fun getMutableLiveData(): MutableLiveData<T> {
        return mutableLiveData
    }

    fun setQuery(text: String) {
        query = text
    }

    fun getQuery(): String {
        return query
    }
}