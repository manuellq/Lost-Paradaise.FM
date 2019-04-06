package com.mlcorrea.lostparadisefm.framework.network

import android.content.Context
import com.mlcorrea.lostparadisefm.framework.extension.networkInfo
import javax.inject.Singleton

/**
 * Created by manuel on 06/04/19
 *
 * Injectable class which handles device network connection.
 */
@Suppress("DEPRECATION")
@Singleton
class NetworkControllerImpl(private val context: Context) : NetworkController {

    override val isConnected: Boolean
        get() {
            return try {
                context.networkInfo?.isConnectedOrConnecting ?: false
            } catch (exception: Exception) {
                false
            }
        }
}