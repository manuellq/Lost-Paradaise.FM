package com.mlcorrea.domain.model

import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel on 07/04/19
 */
data class AlbumPage(
    val totalResults: Long,
    val startIndex: Long,
    val itemsPerPage: Long,
    val albums: List<Album>
) : ViewModelData