package com.example.data.repository

import com.example.data.db.dao.CurrencyDao
import com.example.data.db.dao.LiveDao
import com.example.data.db.dao.RateDao
import com.example.data.model.Currency
import com.example.data.model.Rate
import com.example.data.remote.CurrencyLayerService

class CurrencyRepositoryImpl(
    private val currencyDao: CurrencyDao,
    private val service: CurrencyLayerService
) : CurrencyRepository {
    override suspend fun getCurrencies(): List<Currency> {
        return currencyDao.getAll().let {
            if (it == null || it.isEmpty()) {
                fetchCurrencies()
            } else {
                it
            }
        }
    }

    private suspend fun fetchCurrencies(): List<Currency> {
        return service.getCurrencies().body().let { result ->
            val currencies = result?.currencies?.map {
                Currency(it.key, it.value)
            } ?: emptyList()
            currencyDao.insert(currencies)
            currencies
        }
    }
}