package com.mlcorrea.data.dto.model

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.domain.model.Image
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 07/04/19
 */
@JsonClass(generateAdapter = true)
data class ImageDto(@Json(name = "#text") val text: String, @Json(name = "size") val size: String) : BaseDto<Image> {

    override fun unwrapDto(): Image {
        return Image(text, size)
    }

}