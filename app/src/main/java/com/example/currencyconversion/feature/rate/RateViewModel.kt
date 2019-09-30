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
    val rates = MutableLiveData<List<Rate>>()
    var amount = 123.4F

    fun init() {}

    fun reGetRate(currency: String) {
        if (currency.length != 3) return
        viewModelScope.launch(Dispatchers.IO) {
            changeSource(liveRepository.getRates(currency), currency)
        }
    }

    fun reCalc(amount: Float) {
        this.amount = amount
        val r = rates.value ?: emptyList()
        r.forEach { it.calcResult = it.rate * this.amount }
        rates.postValue(r)
    }

    // TODO: UPGRADE PLAN AND DELETE THIS FUNCTION
    private fun changeSource(rates: List<Rate>, source: String) {
        // free planだとswitching source(default USD)が出来ない
        // 手元でxxみたいな計算するしかない
        val usdRate = rates.find { it.dist == source }?.rate ?: return

        // 例えばJPY -> EUR にしたい場合
        // JPY -> USD -> EUR にする
        // つまり100JPYは
        // JPY -> USD : 100JPY / 107(USD->JPY rate) => 0.93USD
        // USD -> EUR : 0.93USD * 0.91(USD->EUR rate) => 0.84EUR
        // rateは(USD->EUR)/(USD->JPY)になる

        rates.forEach {
            if (it.dist == "USD") {
                it.rate = 1.0F / usdRate
            } else {
                it.rate = it.rate / usdRate
            }
            it.calcResult = amount * it.rate
        }
        this.rates.postValue(rates)
    }
}
