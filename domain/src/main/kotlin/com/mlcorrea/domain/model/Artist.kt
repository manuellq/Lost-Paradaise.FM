package com.mlcorrea.domain.model

import com.mlcorrea.domain.model.adapter.ViewModelData

/**
 * Created by manuel on 07/04/19
 */
data class Artist(
    val name: String,
    val listeners: String?,
    val url: String?,
    val streamable: String?,
    val bid: String?,
    val images: List<Image>
) : ViewModelData