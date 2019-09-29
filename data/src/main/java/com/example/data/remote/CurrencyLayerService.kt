package com.example.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface CurrencyLayerService {
    @GET("live")
    suspend fun getLive(): Response<String>
}
