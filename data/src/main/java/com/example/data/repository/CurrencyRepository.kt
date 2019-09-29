package com.example.data.repository

import com.example.data.model.Currency
import com.example.data.model.Rate

interface CurrencyRepository {
    suspend fun getCurrencies(): List<Currency>
}