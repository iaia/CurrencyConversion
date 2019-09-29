package com.example.currencyconversion.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.LiveRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveRepository: LiveRepository
): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            liveRepository.getLive()
        }
    }
}