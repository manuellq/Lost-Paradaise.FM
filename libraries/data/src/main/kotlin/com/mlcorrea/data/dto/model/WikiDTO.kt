package com.mlcorrea.data.dto.model

import com.mlcorrea.data.dto.base.BaseDto
import com.mlcorrea.domain.model.Wiki
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 08/04/19
 */
@JsonClass(generateAdapter = true)
data class WikiDTO(
    @Json(name = "published") val published: String?, @Json(name = "summary") val summary: String?,
    @Json(name = "content") val content: String?
) : BaseDto<Wiki> {
    override fun unwrapDto(): Wiki {
        return Wiki(published, summary, content)
    }
}