package com.mlcorrea.data.dto.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 07/04/19
 */
@JsonClass(generateAdapter = true)
data class ArtistMatchesDTO(@Json(name = "artist") val artists: List<ArtistDTO>?)
