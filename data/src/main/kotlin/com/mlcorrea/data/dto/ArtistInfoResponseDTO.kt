package com.mlcorrea.data.dto

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.data.dto.model.ArtistDTO
import com.mlcorrea.domain.model.Artist
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 06/04/19
 */
@JsonClass(generateAdapter = true)
data class ArtistInfoResponseDTO(
    @Json(name = "artist")
    val data: ArtistDTO?,
    @Json(name = "error")
    val error: Int?,
    @Json(name = "message")
    val message: String?
) : BaseDto<Artist?> {

    override fun unwrapDto(): Artist? {
        return data?.unwrapDto()
    }
}