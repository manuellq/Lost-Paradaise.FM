package com.mlcorrea.data.dto.model

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.domain.model.Bio
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 07/04/19
 */
@JsonClass(generateAdapter = true)
data class BioDTO(@Json(name = "summary") val summary: String?, @Json(name = "content") val content: String?) :
    BaseDto<Bio> {

    override fun unwrapDto(): Bio {
        return Bio(summary, content)
    }

}