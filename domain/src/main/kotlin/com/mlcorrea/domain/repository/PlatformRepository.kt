package com.mlcorrea.domain.repository

import com.mlcorrea.domain.model.AlbumPage
import io.reactivex.Observable

/**
 * Created by manuel on 06/04/19
 */
interface PlatformRepository {

    fun getAlbums(album: String, page: String, limit: String): Observable<AlbumPage>
}