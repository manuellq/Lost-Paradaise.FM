package com.mlcorrea.data.dto.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 07/04/19
 */
@JsonClass(generateAdapter = true)
data class NetworkResponseDTO<T>(
    @Json(name = "results")
    val data: T?,
    @Json(name = "error")
    val error: Int?,
    @Json(name = "message")
    val message: String?
)