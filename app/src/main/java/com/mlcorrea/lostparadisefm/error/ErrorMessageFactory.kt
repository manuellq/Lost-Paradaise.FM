package com.mlcorrea.lostparadisefm.error

import android.content.Context
import com.mlcorrea.domain.exception.APIError
import com.mlcorrea.domain.exception.HttpNoInternetConnectionException
import com.mlcorrea.lostparadisefm.R

/**
 * Created by manuel on 08/04/19
 */
interface ErrorMessageFactory {

    companion object Factory {

        fun createFromException(context: Context, exception: Exception?): String {
            return when (exception) {
                is HttpNoInternetConnectionException ->
                    context.getString(R.string.exception_message_no_connection)
                is APIError -> {
                    exception.messageError ?: context.getString(R.string.exception_generic_message)
                }
                else -> context.getString(R.string.exception_generic_message)
            }
        }
    }
}

fun ErrorMessageFactory.Factory.fromException(context: Context, exception: Exception?) =
    this.createFromException(context, exception)