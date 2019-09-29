package com.example.currencyconversion.di

import com.example.currencyconversion.feature.amount.AmountViewModel
import com.example.currencyconversion.feature.currency.CurrencyViewModel
import com.example.currencyconversion.feature.main.MainViewModel
import com.example.currencyconversion.feature.rate.RateViewModel
import org.koin.dsl.module

import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { MainViewModel() }
}

val fragmentViewModelModule = module {
    viewModel { RateViewModel(get()) }
    viewModel { CurrencyViewModel(get()) }
    viewModel { AmountViewModel() }
}
