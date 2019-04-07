package com.mlcorrea.data.repository

import com.mlcorrea.data.dto.AlbumsResponseDTO
import com.mlcorrea.data.network.ApiController
import com.mlcorrea.domain.model.AlbumPage
import com.mlcorrea.domain.repository.PlatformRepository
import io.reactivex.Observable

/**
 * Created by manuel on 06/04/19
 */
class PlatformRepositoryImpl constructor(private val apiController: ApiController) : PlatformRepository {


    override fun getAlbums(album: String, page: String, limit: String): Observable<AlbumPage> {
        return apiController.getAlbums(album, page, limit)
            .map { response: AlbumsResponseDTO ->
                response.unwrapDto()
            }
    }

}