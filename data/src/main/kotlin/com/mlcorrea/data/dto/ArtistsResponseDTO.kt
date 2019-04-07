package com.mlcorrea.data.dto

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.data.dto.model.ArtistMatchesDTO
import com.mlcorrea.domain.model.Artist
import com.mlcorrea.domain.model.ArtistPage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 06/04/19
 */
@JsonClass(generateAdapter = true)
data class ArtistsResponseDTO(
    @Json(name = "opensearch:totalResults")
    val totalResults: Long?,
    @Json(name = "opensearch:startIndex")
    val startIndex: Long?,
    @Json(name = "opensearch:itemsPerPage")
    val itemsPerPage: Long?,
    @Json(name = "albummatches")
    val artistMatches: ArtistMatchesDTO?
) : BaseDto<ArtistPage> {

    override fun unwrapDto(): ArtistPage {
        val artistList: MutableList<Artist> = mutableListOf()
        artistMatches?.artists?.let {
            for (artist in it) {
                artistList.add(artist.unwrapDto())
            }
        }

        return ArtistPage(totalResults ?: 0, startIndex ?: 0, itemsPerPage ?: 0, artistList)
    }

}