package com.mlcorrea.lostparadisefm.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mlcorrea.domain.model.adapter.ViewModelData
import com.mlcorrea.domain.network.NetworkRequestState

/**
 * Created by manuel on 07/04/19
 */
abstract class BaseDataSource : PageKeyedDataSource<Long, ViewModelData>() {

    private val networkState = MutableLiveData<NetworkRequestState>()

    fun setNetworkState(status: NetworkRequestState) {
        networkState.value = status
    }

    fun getNetworkState(): MutableLiveData<NetworkRequestState> {
        return networkState
    }
}