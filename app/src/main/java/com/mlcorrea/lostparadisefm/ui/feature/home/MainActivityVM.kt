package com.mlcorrea.lostparadisefm.ui.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by manuel on 07/04/19
 */
class MainActivityVM  constructor() : ViewModel() {

    val queryUser: MutableLiveData<String> = MutableLiveData()

    fun setQuery(text: String) {
        queryUser.value = text
    }
}