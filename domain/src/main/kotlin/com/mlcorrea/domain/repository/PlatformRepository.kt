package com.mlcorrea.domain.repository

import com.mlcorrea.domain.model.AlbumPage
import com.mlcorrea.domain.model.ArtistPage
import com.mlcorrea.domain.model.TrackPage
import io.reactivex.Observable

/**
 * Created by manuel on 06/04/19
 */
interface PlatformRepository {

    fun getAlbums(album: String, page: String, limit: String): Observable<AlbumPage>

    fun getArtists(artist: String, page: String, limit: String): Observable<ArtistPage>

    fun getTracks(track: String, page: String, limit: String): Observable<TrackPage>

}