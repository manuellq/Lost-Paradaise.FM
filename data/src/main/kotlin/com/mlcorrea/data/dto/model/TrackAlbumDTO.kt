package com.mlcorrea.data.dto.model

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.domain.model.Image
import com.mlcorrea.domain.model.Track
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 08/04/19
 */
@JsonClass(generateAdapter = true)
class TrackAlbumDTO(
    @Json(name = "name")
    val name: String,
    @Json(name = "artist")
    val artist: ArtistDTO?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "listeners")
    val listeners: String?,
    @Json(name = "image")
    val images: List<ImageDto>?,
    @Json(name = "duration")
    val duration: String?,
    @Json(name = "@attr")
    val attr: AttrDTO?
) : BaseDto<Track> {

    override fun unwrapDto(): Track {
        val imageList: MutableList<Image> = mutableListOf()
        images?.let {
            for (image in it) {
                imageList.add(image.unwrapDto())
            }
        }

        return Track(name, artist?.name, url, listeners, imageList, duration, attr?.rank)
    }
}