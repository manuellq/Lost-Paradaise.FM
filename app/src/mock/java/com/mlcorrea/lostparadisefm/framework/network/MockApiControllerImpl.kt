package com.mlcorrea.lostparadisefm.framework.network

import android.content.Context
import com.mlcorrea.data.dto.AlbumsResponseDTO
import com.mlcorrea.data.dto.ArtistsResponseDTO
import com.mlcorrea.data.dto.TracksResponseDTO
import com.mlcorrea.data.dto.model.AlbumDto
import com.mlcorrea.data.dto.model.ArtistDTO
import com.mlcorrea.data.dto.model.TrackInfoDTO
import com.mlcorrea.data.network.ApiController
import io.reactivex.Observable

/**
 * Created by manuel on 06/04/19
 */
class MockApiControllerImpl(private val context: Context) : ApiController {

    override fun getAlbums(album: String, page: String, limit: String): Observable<AlbumsResponseDTO> {
        return MockData.getAlbumList(context)
    }

    override fun getArtist(album: String, page: String, limit: String): Observable<ArtistsResponseDTO> {
        return MockData.getArtistList(context)
    }

    override fun getTrack(album: String, page: String, limit: String): Observable<TracksResponseDTO> {
        return MockData.getTrackList(context)
    }

    override fun getAlbumInfo(artist: String, album: String): Observable<AlbumDto> {
        return MockData.getAlbumInfo(context)
    }

    override fun getArtistInfo(artist: String): Observable<ArtistDTO> {
        return MockData.getArtistInfo(context)
    }

    override fun getTrackInfo(artist: String, track: String): Observable<TrackInfoDTO> {
        return MockData.getTrackInfo(context)
    }


}