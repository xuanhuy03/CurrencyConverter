package com.huyngo.projecthuyandroid.myconverter.data.error.mapper

import android.content.Context
import com.huyngo.projecthuyandroid.R
import com.huyngo.projecthuyandroid.myconverter.data.error.INVALID_INPUT
import com.huyngo.projecthuyandroid.myconverter.data.error.NETWORK_ERROR
import com.huyngo.projecthuyandroid.myconverter.data.error.NOT_ALLOWED
import com.huyngo.projecthuyandroid.myconverter.data.error.NOT_FOUND
import com.huyngo.projecthuyandroid.myconverter.data.error.NO_INTERNET_CONNECTION
import com.huyngo.projecthuyandroid.myconverter.data.error.UNAUTHORIZED
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMapper @Inject constructor(@ApplicationContext val context: Context) :
    ErrorMapperSource {
    override fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf<Int, String>(
            Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
            Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),
            Pair(INVALID_INPUT, getErrorString(R.string.invalid_input)),
            Pair(UNAUTHORIZED, getErrorString(R.string.unauthorized)),
            Pair(NOT_ALLOWED, getErrorString(R.string.not_allowed)),
            Pair(NOT_FOUND, getErrorString(R.string.not_exist))
        ).withDefault {
            getErrorString(R.string.network_error)
        }
}