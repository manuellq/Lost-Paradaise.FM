package com.mlcorrea.data.dto.model

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.domain.model.Image
import com.squareup.moshi.Json

/**
 * Created by manuel on 07/04/19
 */
data class ArtistDTO(
    @Json(name = "name")
    val name: String,
    @Json(name = "listeners")
    val listeners: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "streamable")
    val streamable: String?,
    @Json(name = "mbid")
    val bid: String?,
    @Json(name = "image")
    val images: List<ImageDto>?
) : BaseDto<Artist> {

    override fun unwrapDto(): Artist {
        val imageList: MutableList<Image> = mutableListOf()
        images?.let {
            for (image in it) {
                imageList.add(image.unwrapDto())
            }
        }

        return Artist(name, listeners, url, streamable, bid, imageList)
    }

}
