package com.mlcorrea.domain.model

/**
 * Created by manuel on 07/04/19
 */
data class ArtistPage(
    val totalResults: Long,
    val startIndex: Long,
    val itemsPerPage: Long,
    val artists: List<Artist>
)