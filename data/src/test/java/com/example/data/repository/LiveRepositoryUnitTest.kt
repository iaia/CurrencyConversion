package com.example.data.repository

import com.example.data.db.dao.LiveDao
import com.example.data.db.dao.RateDao
import com.example.data.model.Live
import com.example.data.model.Rate
import com.example.data.remote.CurrencyLayerService
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LiveRepositoryUnitTest {
    lateinit var repository: LiveRepository

    lateinit var service: CurrencyLayerService
    @Mock
    lateinit var liveDao: LiveDao
    @Mock
    lateinit var rateDao: RateDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = mock {
            on {
                runBlocking {
                    getLive(any())
                }
            } doReturn retrofit2.Response.success(
                Live(
                    true, 100, "USD",
                    mapOf("USDEUR" to 100F, "USDJPY" to 101F, "USDUSD" to 1F)
                )
            )
        }

        repository = LiveRepositoryImpl(liveDao, rateDao, service)
    }

    @Test
    fun shouldCallGetLive_whenNoLocalData() {
        whenever(liveDao.getRecent("USD")).thenReturn(null)
        runBlocking {
            repository.getRates("USD")
            verify(service).getLive(any())
        }
    }

    @Test
    fun shouldReturn_is1Data() {
        whenever(rateDao.getAll()).thenReturn(listOf(Rate("JPY", "USD", 107F, 0F, 123)))
        runBlocking {
            assertThat(repository.getRates("USD").size, `is`(1))
        }
    }
}