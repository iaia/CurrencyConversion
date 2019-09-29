package com.example.currencyconversion.di

import com.example.currencyconversion.feature.main.MainViewModel
import org.koin.dsl.module

import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

/*
val fragmentViewModelModule = module {
    viewModel { MainFragmentViewModel() }
    viewModel { CameraViewModel() }
}
*/