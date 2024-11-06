package com.huyngo.projecthuyandroid.myconverter.data.remote.remote.service

import com.huyngo.projecthuyandroid.myconverter.data.dto.ExchangeRateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateService {
    @GET("latest")
    suspend fun getLatestExchangeRate(
        @Query("base_currency") base: String,
        @Query("currencies") currencies: String
    ): Response<ExchangeRateResponse>
}