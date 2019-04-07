package com.mlcorrea.data.dto.model

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.domain.model.Album
import com.mlcorrea.domain.model.Image
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 07/04/19
 */
@JsonClass(generateAdapter = true)
data class AlbumDto(
    @Json(name = "name")
    val name: String,
    @Json(name = "artist")
    val artist: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "streamable")
    val streamable: String?,
    @Json(name = "mbid")
    val bid: String?,
    @Json(name = "image")
    val images: List<ImageDto>?,
    @Json(name = "TracksDto")
    val tracks: TracksDto?
) : BaseDto<Album> {

    override fun unwrapDto(): Album {
        val imageList: MutableList<Image> = mutableListOf()
        images?.let {
            for (image in it) {
                imageList.add(image.unwrapDto())
            }
        }

        return Album(name, artist, url, streamable, bid, imageList, tracks?.unwrapDto() ?: emptyList())
    }

}