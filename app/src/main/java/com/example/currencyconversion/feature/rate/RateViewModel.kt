package com.example.currencyconversion.feature.rate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Rate
import com.example.data.repository.LiveRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RateViewModel(
    private val liveRepository: LiveRepository
) : ViewModel() {
    var rates = MutableLiveData<List<Rate>>()
    var inputAmount = 100F

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            // sourceを指定
            val rates = liveRepository.getRates()
            rates.forEach {
                it.calcResult = it.rate * inputAmount
            }
            this@RateViewModel.rates.postValue(rates)
        }
    }
}
