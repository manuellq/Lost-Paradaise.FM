package com.mlcorrea.lostparadisefm.framework.retrofit.repository

import com.mlcorrea.data.dto.AlbumsResponseDTO
import com.mlcorrea.data.dto.ArtistsResponseDTO
import com.mlcorrea.data.dto.base.NetworkResponseDTO
import com.mlcorrea.data.network.ApiController
import com.mlcorrea.domain.exception.APIError
import com.mlcorrea.lostparadisefm.framework.retrofit.apimanager.ApiManager
import io.reactivex.Observable

/**
 * Created by manuel on 06/04/19
 */
class ApiControllerImpl(private val apiManager: ApiManager) :
    ApiController {


    override fun getAlbums(album: String, page: String, limit: String): Observable<AlbumsResponseDTO> {
        return apiManager.apiServices.getAlbums(album, limit, page)
            .map { response: NetworkResponseDTO<AlbumsResponseDTO> ->
                return@map if (response.data == null) {
                    throw APIError(response.error, response.message)
                } else {
                    response.data
                }
            }
    }

    override fun getArtist(album: String, page: String, limit: String): Observable<ArtistsResponseDTO> {
        return apiManager.apiServices.getArtists(album, limit, page)
            .map { response: NetworkResponseDTO<ArtistsResponseDTO> ->
                return@map if (response.data == null) {
                    throw APIError(response.error, response.message)
                } else {
                    response.data
                }
            }
    }

}