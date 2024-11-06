package com.huyngo.projecthuyandroid.myconverter.data

import com.huyngo.projecthuyandroid.myconverter.data.dto.ExchangeRates
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun getLatestRates(base: String, currencies: String): Flow<Resource<ExchangeRates>>
}