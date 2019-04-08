package com.mlcorrea.domain.model

import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel on 07/04/19
 */
data class Track(
    val name: String,
    val artist: String?,
    val url: String?,
    val listeners: String?,
    val images: List<Image>,
    val duration: String?,
    val rank: Int?
) : ViewModelData