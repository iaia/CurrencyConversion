package com.example.currencyconversion.rate

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconversion.feature.rate.RateViewModel
import com.example.data.model.Rate
import com.example.data.repository.LiveRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class RateViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var viewModel: RateViewModel

    @Mock
    lateinit var repository: LiveRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        repository.apply {
            whenever(runBlocking { getRates(ArgumentMatchers.anyString()) }).then { }
        }

        viewModel = RateViewModel(repository)
    }

    @Test
    fun shouldCorrectRate_whenSourceCurrencyIsJPY() {
        repository.apply {
            whenever(runBlocking { getRates("JPY") }).thenReturn(
                listOf(
                    Rate("JPY", "USD", 107F, 0F, 123L),
                    Rate("USD", "USD", 1F, 0F, 123L)

                )
            )
        }
        val latch = CountDownLatch(1)
        viewModel.rates.observeForever {
            latch.countDown()
        }
        viewModel.reGetRate("JPY")

        latch.await(2, TimeUnit.SECONDS)
        val rates = viewModel.rates.value ?: throw Exception()
        assertThat(rates.find { it.dist == "USD" }?.rate, `is`(0.009345794F))
        assertThat(rates.find { it.dist == "JPY" }?.rate, `is`(1.0F))
    }

    @Test
    fun shouldCorrectRateConversion_whenSourceCurrencyIsJPY() {
        repository.apply {
            whenever(runBlocking { getRates("JPY") }).thenReturn(
                listOf(
                    Rate("JPY", "USD", 107F, 0F, 123L),
                    Rate("USD", "USD", 1F, 0F, 123L)

                )
            )
        }
        val latch = CountDownLatch(2)
        viewModel.rates.observeForever {
            latch.countDown()
        }
        viewModel.reGetRate("JPY")
        viewModel.reCalc(100F)

        latch.await(2, TimeUnit.SECONDS)
        val rates = viewModel.rates.value ?: throw Exception()
        assertThat(rates.find { it.dist == "USD" }?.calcResult, `is`(0.93457943F))
        assertThat(rates.find { it.dist == "JPY" }?.calcResult, `is`(100F))
    }
}