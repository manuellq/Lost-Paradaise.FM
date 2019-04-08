package com.mlcorrea.data.dto.model

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.domain.model.Similar
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 07/04/19
 */
@JsonClass(generateAdapter = true)
data class SimilarDTO(@Json(name = "artist") val artists: List<ArtistDTO>?) : BaseDto<Similar> {

    override fun unwrapDto(): Similar {
        val artistList: MutableList<Artist> = mutableListOf()
        artists?.let {
            for (artist in it) {
                artistList.add(artist.unwrapDto())
            }
        }
        return Similar(artistList)
    }

}