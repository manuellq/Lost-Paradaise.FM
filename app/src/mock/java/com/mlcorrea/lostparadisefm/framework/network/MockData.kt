package com.mlcorrea.lostparadisefm.framework.network

import android.content.Context
import com.mlcorrea.data.dto.*
import com.mlcorrea.data.dto.model.AlbumDto
import com.mlcorrea.data.dto.model.ArtistDTO
import com.mlcorrea.data.dto.model.TrackInfoDTO
import com.squareup.moshi.Moshi
import io.reactivex.Observable


/**
 * Created by manuel on 08/04/19
 */
object MockData {

    fun getAlbumList(context: Context): Observable<AlbumsResponseDTO> {
        return Observable.just("albumList.Json")
            .map { name: String ->
                val json = context.assets.open(name).bufferedReader().use { it.readText() }
                json
            }
            .map { response: String ->
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter<AlbumsResponseDTO>(AlbumsResponseDTO::class.java)
                jsonAdapter.fromJson(response)
            }
    }

    fun getArtistList(context: Context): Observable<ArtistsResponseDTO> {
        return Observable.just("artistList.json")
            .map { name: String ->
                val json = context.assets.open(name).bufferedReader().use { it.readText() }
                json
            }
            .map { response: String ->
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter<ArtistsResponseDTO>(ArtistsResponseDTO::class.java)
                val artistsResponseDTO: ArtistsResponseDTO? = jsonAdapter.fromJson(response)
                artistsResponseDTO ?: throw IllegalStateException("ArtistsResponseDTO DTO cannot be null")
            }
    }

    fun getTrackList(context: Context): Observable<TracksResponseDTO> {
        return Observable.just("trackList.json")
            .map { name: String ->
                val json = context.assets.open(name).bufferedReader().use { it.readText() }
                json
            }
            .map { response: String ->
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter<TracksResponseDTO>(TracksResponseDTO::class.java)
                val tracksResponseDTO: TracksResponseDTO? = jsonAdapter.fromJson(response)
                tracksResponseDTO ?: throw IllegalStateException("TracksResponseDTO DTO cannot be null")
            }
    }

    fun getAlbumInfo(context: Context): Observable<AlbumDto> {
        return Observable.just("albumInfo.json")
            .map { name: String ->
                val json = context.assets.open(name).bufferedReader().use { it.readText() }
                json
            }
            .map { response: String ->
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter<AlbumInfoResponseDTO>(AlbumInfoResponseDTO::class.java)
                val albumInfoResponseDTO: AlbumInfoResponseDTO? = jsonAdapter.fromJson(response)
                albumInfoResponseDTO?.data ?: throw IllegalStateException("AlbumInfoResponseDTO DTO cannot be null")
            }
    }

    fun getArtistInfo(context: Context): Observable<ArtistDTO> {
        return Observable.just("artistInfo.json")
            .map { name: String ->
                val json = context.assets.open(name).bufferedReader().use { it.readText() }
                json
            }
            .map { response: String ->
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter<ArtistInfoResponseDTO>(ArtistInfoResponseDTO::class.java)
                val artistInfoResponseDTO: ArtistInfoResponseDTO? = jsonAdapter.fromJson(response)
                artistInfoResponseDTO?.data ?: throw IllegalStateException("ArtistInfoResponseDTO DTO cannot be null")
            }
    }

    fun getTrackInfo(context: Context): Observable<TrackInfoDTO> {
        return Observable.just("trackInfo.json")
            .map { name: String ->
                val json = context.assets.open(name).bufferedReader().use { it.readText() }
                json
            }
            .map { response: String ->
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter<TrackInfoResponseDTO>(TrackInfoResponseDTO::class.java)
                val trackInfoResponseDTO: TrackInfoResponseDTO? = jsonAdapter.fromJson(response)
                trackInfoResponseDTO?.data ?: throw IllegalStateException("TrackInfoResponseDTO DTO cannot be null")
            }
    }

}