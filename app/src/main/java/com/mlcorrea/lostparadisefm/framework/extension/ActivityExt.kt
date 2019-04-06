package com.mlcorrea.lostparadisefm.framework.extension

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Created by manuel on 06/04/19
 */
//inline fun <reified T : ViewModel> FragmentActivity.viewModelInit(
//    factory: ViewModelProvider.Factory,
//    body: T.() -> Unit
//): T {
//    val vm = ViewModelProviders.of(this, factory)[T::class.java]
//    vm.body()
//    return vm
//}