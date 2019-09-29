package com.example.currencyconversion.feature.rate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.LiveRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RateViewModel(
    private val liveRepository: LiveRepository
) : ViewModel() {

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            liveRepository.getLive()
        }
    }
}
