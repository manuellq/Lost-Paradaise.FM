package com.mlcorrea.domain.model

import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel on 07/04/19
 */
data class Album(
    val name: String,
    val artist: String?,
    val url: String?,
    val streamable: String?,
    val bid: String?,
    val images: List<Image>,
    val tracks: List<Track> = emptyList()
) : ViewModelData