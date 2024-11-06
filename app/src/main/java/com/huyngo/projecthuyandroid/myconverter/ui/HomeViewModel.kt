package com.huyngo.projecthuyandroid.myconverter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huyngo.projecthuyandroid.myconverter.data.DataRepositorySource
import com.huyngo.projecthuyandroid.myconverter.data.Resource
import com.huyngo.projecthuyandroid.myconverter.data.dto.ExchangeRates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataRepository: DataRepositorySource
) : ViewModel() {

    private val exchangeRateLiveDataPrivate = MutableLiveData<Resource<ExchangeRates>>()
    val exchangeRateLiveData: LiveData<Resource<ExchangeRates>> get() = exchangeRateLiveDataPrivate

    fun getExchangeRate(fromCurrency: String, toCurrency: String) {
        viewModelScope.launch {
            exchangeRateLiveDataPrivate.postValue(Resource.Loading())
            dataRepository.getLatestRates(fromCurrency, toCurrency).collect {
                exchangeRateLiveDataPrivate.postValue(it)
            }
        }
    }

}