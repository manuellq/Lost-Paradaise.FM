package com.mlcorrea.lostparadisefm.ui.utils

import com.mlcorrea.domain.enum.TypeImage
import com.mlcorrea.domain.model.Image

/**
 * Created by manuel on 08/04/19
 */

fun List<Image>?.getUrlImage(typeImage: TypeImage): String? {
    if (this == null) return null
    for (image in this) {
        if (image.size == typeImage.code) {
            return image.text
        }
    }
    return null
}