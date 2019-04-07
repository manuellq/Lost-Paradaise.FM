package com.mlcorrea.data.network

import com.mlcorrea.data.dto.AlbumsResponseDTO
import io.reactivex.Observable

/**
 * Created by manuel on 06/04/19
 */
interface ApiController {

    fun getAlbums(album: String, page: String, limit: String): Observable<AlbumsResponseDTO>
}