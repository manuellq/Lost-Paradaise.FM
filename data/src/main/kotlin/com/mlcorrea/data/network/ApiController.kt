package com.mlcorrea.data.network

import com.mlcorrea.data.dto.AlbumsResponseDTO
import com.mlcorrea.data.dto.ArtistsResponseDTO
import com.mlcorrea.data.dto.TracksResponseDTO
import com.mlcorrea.data.dto.model.AlbumDto
import com.mlcorrea.data.dto.model.ArtistDTO
import com.mlcorrea.data.dto.model.TrackInfoDTO
import io.reactivex.Observable

/**
 * Created by manuel on 06/04/19
 */
interface ApiController {

    fun getAlbums(album: String, page: String, limit: String): Observable<AlbumsResponseDTO>

    fun getArtist(album: String, page: String, limit: String): Observable<ArtistsResponseDTO>

    fun getTrack(album: String, page: String, limit: String): Observable<TracksResponseDTO>

    fun getAlbumInfo(artist: String, album: String): Observable<AlbumDto>

    fun getArtistInfo(artist: String): Observable<ArtistDTO>

    fun getTrackInfo(artist: String, track: String): Observable<TrackInfoDTO>
}