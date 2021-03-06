package com.mlcorrea.lostparadisefm.framework.retrofit.service

import com.mlcorrea.data.dto.*
import com.mlcorrea.data.dto.base.NetworkResponseDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by manuel on 06/04/19
 *
 * Interface defining all endpoints used by the application
 */
interface ApiService {

    //Albums
    @GET("/2.0/?method=album.search")
    fun getAlbums(@Query("album") album: String, @Query("limit") limit: String, @Query("page") page: String): Observable<NetworkResponseDTO<AlbumsResponseDTO>>

    @GET("/2.0/?method=album.getinfo")
    fun getAlbumInfo(@Query("artist") artist: String, @Query("album") album: String): Observable<AlbumInfoResponseDTO>


    //Artist
    @GET("/2.0/?method=artist.search")
    fun getArtists(@Query("artist") artist: String, @Query("limit") limit: String, @Query("page") page: String):  Observable<NetworkResponseDTO<ArtistsResponseDTO>>

    @GET("/2.0/?method=artist.getinfo")
    fun getArtistInfo(@Query("artist") artist: String): Observable<ArtistInfoResponseDTO>


    //Tracks
    @GET("/2.0/?method=track.search")
    fun getTracks(@Query("track") track: String, @Query("limit") limit: String, @Query("page") page: String): Observable<NetworkResponseDTO<TracksResponseDTO>>

    @GET("/2.0/?method=track.getInfo")
    fun getTrackInfo(@Query("track") track: String, @Query("artist") artist: String): Observable<TrackInfoResponseDTO>

}