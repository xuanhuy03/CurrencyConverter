package com.huyngo.projecthuyandroid.myconverter.data

import com.huyngo.projecthuyandroid.myconverter.data.dto.ExchangeRates
import com.huyngo.projecthuyandroid.myconverter.data.remote.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {
    override suspend fun getLatestRates(
        base: String,
        currencies: String
    ): Flow<Resource<ExchangeRates>> {
        return flow {
            emit(remoteRepository.getLatestExchangeRate(base, currencies))
        }.flowOn(ioDispatcher)
    }

}