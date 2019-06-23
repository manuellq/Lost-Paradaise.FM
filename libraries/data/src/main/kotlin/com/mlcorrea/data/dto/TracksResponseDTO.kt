package com.mlcorrea.data.dto

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.data.dto.model.TrackMatchesDTO
import com.mlcorrea.domain.model.Track
import com.mlcorrea.domain.model.TrackPage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 06/04/19
 */
@JsonClass(generateAdapter = true)
class TracksResponseDTO(
    @Json(name = "opensearch:totalResults")
    val totalResults: Long?,
    @Json(name = "opensearch:startIndex")
    val startIndex: Long?,
    @Json(name = "opensearch:itemsPerPage")
    val itemsPerPage: Long?,
    @Json(name = "trackmatches")
    val trackmatches: TrackMatchesDTO?
) : BaseDto<TrackPage> {

    override fun unwrapDto(): TrackPage {
        val trackList: MutableList<Track> = mutableListOf()
        trackmatches?.tracks?.let {
            for (track in it) {
                trackList.add(track.unwrapDto())
            }
        }

        return TrackPage(totalResults ?: 0, startIndex ?: 0, itemsPerPage ?: 0, trackList)
    }
}