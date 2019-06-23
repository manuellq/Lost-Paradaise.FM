package com.mlcorrea.data.dto

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.data.dto.model.AlbumDto
import com.mlcorrea.domain.model.Album
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 06/04/19
 */
@JsonClass(generateAdapter = true)
class AlbumInfoResponseDTO(
    @Json(name = "album")
    val data: AlbumDto?,
    @Json(name = "error")
    val error: Int?,
    @Json(name = "message")
    val message: String?
) : BaseDto<Album?> {

    override fun unwrapDto(): Album? {
        return data?.unwrapDto()
    }
}