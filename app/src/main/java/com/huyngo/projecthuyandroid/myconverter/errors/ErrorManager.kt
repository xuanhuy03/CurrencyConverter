package com.huyngo.projecthuyandroid.myconverter.errors

import com.huyngo.projecthuyandroid.myconverter.data.error.AppError
import com.huyngo.projecthuyandroid.myconverter.data.error.mapper.ErrorMapper
import javax.inject.Inject

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): AppError {
        return AppError(code= errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}