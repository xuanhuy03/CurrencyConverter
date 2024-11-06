package com.huyngo.projecthuyandroid.myconverter.errors

import com.huyngo.projecthuyandroid.myconverter.data.error.AppError

interface ErrorUseCase {
    fun getError(errorCode: Int) : AppError
}