package com.mlcorrea.data.dto.model

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.domain.model.Track
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 07/04/19
 */
@JsonClass(generateAdapter = true)
data class TracksDto(@Json(name = "tracks") val tracks: List<TrackDTO>?) : BaseDto<List<Track>> {

    override fun unwrapDto(): List<Track> {
        val trackList: MutableList<Track> = mutableListOf()
        tracks?.let {
            for (track in it) {
                trackList.add(track.unwrapDto())
            }
        }
        return trackList
    }
}