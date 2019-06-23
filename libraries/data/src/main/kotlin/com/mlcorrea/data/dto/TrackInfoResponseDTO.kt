package com.mlcorrea.data.dto

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.data.dto.model.TrackInfoDTO
import com.mlcorrea.domain.model.Track
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 06/04/19
 */
@JsonClass(generateAdapter = true)
class TrackInfoResponseDTO(
    @Json(name = "track")
    val data: TrackInfoDTO?,
    @Json(name = "error")
    val error: Int?,
    @Json(name = "message")
    val message: String?
) : BaseDto<Track?> {

    override fun unwrapDto(): Track? {
        return data?.unwrapDto()
    }
}