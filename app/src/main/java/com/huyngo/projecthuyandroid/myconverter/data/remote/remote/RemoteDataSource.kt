package com.huyngo.projecthuyandroid.myconverter.data.remote.remote

import com.huyngo.projecthuyandroid.myconverter.data.Resource
import com.huyngo.projecthuyandroid.myconverter.data.dto.ExchangeRates

internal interface RemoteDataSource {
    suspend fun getLatestExchangeRate(base: String, currencies: String): Resource<ExchangeRates>
}