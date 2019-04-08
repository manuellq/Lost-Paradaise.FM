package com.mlcorrea.data.repository

import com.mlcorrea.data.dto.AlbumsResponseDTO
import com.mlcorrea.data.dto.ArtistsResponseDTO
import com.mlcorrea.data.dto.TracksResponseDTO
import com.mlcorrea.data.dto.model.AlbumDto
import com.mlcorrea.data.dto.model.ArtistDTO
import com.mlcorrea.data.dto.model.TrackInfoDTO
import com.mlcorrea.data.network.ApiController
import com.mlcorrea.domain.model.*
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

    override fun getArtists(artist: String, page: String, limit: String): Observable<ArtistPage> {
        return apiController.getArtist(artist, page, limit)
            .map { response: ArtistsResponseDTO ->
                response.unwrapDto()
            }
    }

    override fun getTracks(track: String, page: String, limit: String): Observable<TrackPage> {
        return apiController.getTrack(track, page, limit)
            .map { response: TracksResponseDTO ->
                response.unwrapDto()
            }
    }

    override fun getAlbumInfo(artist: String, album: String): Observable<Album> {
        return apiController.getAlbumInfo(artist, album)
            .map { response: AlbumDto ->
                response.unwrapDto()
            }
    }

    override fun getArtistInfo(artist: String): Observable<Artist> {
        return apiController.getArtistInfo(artist)
            .map { response: ArtistDTO ->
                response.unwrapDto()
            }
    }

    override fun getTrackInfo(artist: String, track: String): Observable<Track> {
        return apiController.getTrackInfo(artist, track)
            .map { response: TrackInfoDTO ->
                response.unwrapDto()
            }
    }
}