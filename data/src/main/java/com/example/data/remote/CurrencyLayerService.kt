package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.model.CurrenciesResult
import com.example.data.model.Live
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyLayerService {
    @GET("live?access_key=${BuildConfig.CURRENCY_LAYER_API_KEY}&format=1")
    suspend fun getLive(): Response<Live>

    @GET("list?access_key=${BuildConfig.CURRENCY_LAYER_API_KEY}&format=1")
    suspend fun getCurrencies(): Response<CurrenciesResult>
}
