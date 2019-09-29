package com.example.currencyconversion.feature.amount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AmountViewModel : ViewModel() {
    val amount = MutableLiveData<Float>().apply {
        postValue(123.4F)
    }
    /*
    val amountStr = MutableLiveData<String>().apply {
        postValue("123.4")
    }
    val amount = MediatorLiveData<Float>().apply {
        addSource(amountStr) {
            value = if (it.isEmpty()) {
                0.0F
            } else {
                try {
                    it.toFloat()
                } catch (e: Exception) {
                    0.0F
                }
            }
        }
    }
    */
}
