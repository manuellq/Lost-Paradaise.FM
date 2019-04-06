package com.mlcorrea.lostparadisefm.framework.retrofit.repository

import com.mlcorrea.data.network.ApiController
import com.mlcorrea.lostparadisefm.framework.retrofit.apimanager.ApiManager

/**
 * Created by manuel on 06/04/19
 */
class ApiControllerImpl(private val apiManager: ApiManager) :
    ApiController {

    override fun getAlbums(album: String, page: String, limit: String) {

    }
}