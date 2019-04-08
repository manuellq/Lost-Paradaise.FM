package com.mlcorrea.data.dto

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.data.dto.model.AlbumMatchesDto
import com.mlcorrea.domain.model.Album
import com.mlcorrea.domain.model.AlbumPage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 06/04/19
 */
@JsonClass(generateAdapter = true)
data class AlbumsResponseDTO(
    @Json(name = "opensearch:totalResults")
    val totalResults: Long?,
    @Json(name = "opensearch:startIndex")
    val startIndex: Long?,
    @Json(name = "opensearch:itemsPerPage")
    val itemsPerPage: Long?,
    @Json(name = "albummatches")
    val albumMatches: AlbumMatchesDto?
) : BaseDto<AlbumPage> {


    override fun unwrapDto(): AlbumPage {
        val albumList: MutableList<Album> = mutableListOf()
        albumMatches?.albums?.let {
            for (album in it) {
                albumList.add(album.unwrapDto())
            }
        }

        return AlbumPage(totalResults ?: 0, startIndex ?: 0, itemsPerPage ?: 0, albumList)
    }
}
