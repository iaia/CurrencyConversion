package com.example.currencyconversion.feature.currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    val currencies = MutableLiveData<List<String>>()

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            currencies.postValue(currencyRepository.getCurrencies().map { it.code })
        }
    }
}
