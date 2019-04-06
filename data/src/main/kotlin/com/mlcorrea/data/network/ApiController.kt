package com.mlcorrea.data.network

/**
 * Created by manuel on 06/04/19
 */
interface ApiController {

    fun getAlbums(album: String, page: String, limit: String)
}