package com.example.data.repository

import com.example.data.model.Currency

interface CurrencyRepository {
    suspend fun getCurrencies(): List<Currency>
}