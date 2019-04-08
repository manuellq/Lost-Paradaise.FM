package com.mlcorrea.domain.repository

import com.mlcorrea.domain.model.*
import io.reactivex.Observable

/**
 * Created by manuel on 06/04/19
 */
interface PlatformRepository {

    fun getAlbums(album: String, page: String, limit: String): Observable<AlbumPage>

    fun getArtists(artist: String, page: String, limit: String): Observable<ArtistPage>

    fun getTracks(track: String, page: String, limit: String): Observable<TrackPage>

    fun getAlbumInfo(artist: String, album: String): Observable<Album>

    fun getArtistInfo(artist: String): Observable<Artist>

    fun getTrackInfo(artist: String, track: String): Observable<Track>

}