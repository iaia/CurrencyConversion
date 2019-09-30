package com.example.currencyconversion.feature.rate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val amount = MutableLiveData<Float>()
    val currency = MutableLiveData<String>()
}