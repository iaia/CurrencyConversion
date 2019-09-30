package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.model.CurrenciesResult
import com.example.data.model.Live
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CurrencyLayerService {
    @GET("live")
    suspend fun getLive(@QueryMap options: Map<String, String>): Response<Live>

    @GET("list?access_key=${BuildConfig.CURRENCY_LAYER_API_KEY}&format=1")
    suspend fun getCurrencies(): Response<CurrenciesResult>
}
