package com.mlcorrea.lostparadisefm.framework.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by manuel on 06/04/19
 */

val Context.networkInfo: NetworkInfo?
    get() =
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
