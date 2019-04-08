package com.mlcorrea.domain.exception

/**
 * Created by manuel on 07/04/19
 */
data class APIError(val error: Int?, val messageError: String?) : RuntimeException(messageError)