package com.mlcorrea.data.dto.base

/**
 * Created by manuel on 06/04/19
 */
interface BaseDto<T> {

    fun unwrapDto(): T
}