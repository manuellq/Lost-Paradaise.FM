package com.mlcorrea.data.dto.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 08/04/19
 */
@JsonClass(generateAdapter = true)
data class AttrDTO(@Json(name = "rank") val rank: Int)