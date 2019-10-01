package com.example.data.repository

import com.example.data.db.dao.CurrencyDao
import com.example.data.model.CurrenciesResult
import com.example.data.model.Currency
import com.example.data.remote.CurrencyLayerService
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CurrencyRepositoryUnitTest {
    lateinit var repository: CurrencyRepository

    lateinit var service: CurrencyLayerService
    @Mock
    lateinit var currencyDao: CurrencyDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = mock {
            on {
                runBlocking { getCurrencies() }
            } doReturn retrofit2.Response.success(CurrenciesResult())
        }
        repository = CurrencyRepositoryImpl(currencyDao, service)
    }

    @Test
    fun shouldCallGetCurrency_whenNoLocalData() {
        whenever(currencyDao.getAll()).thenReturn(null)
        runBlocking {
            repository.getCurrencies()
            verify(service).getCurrencies()
        }
    }

    @Test
    fun shouldNotCallGetCurrency_whenExistsLocalData() {
        whenever(currencyDao.getAll()).thenReturn(listOf(Currency("JPY", "japanese yen")))
        runBlocking {
            repository.getCurrencies()
            verify(service, never()).getCurrencies()
        }
    }
}