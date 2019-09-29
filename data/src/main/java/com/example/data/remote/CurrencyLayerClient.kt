package com.example.data.remote

import com.example.data.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object CurrencyLayerClient {
    fun build(): CurrencyLayerService {
        return buildRetrofit().create(CurrencyLayerService::class.java)
    }

    /*
    fun buildMock(context: Context): CurrencyLayerService {
        val builder = buildRetrofit()

        val behavior = NetworkBehavior.create().apply {
            setDelay(100, TimeUnit.MILLISECONDS)
            setFailurePercent(0)
            setErrorPercent(0)
        }

        val mockRetrofit = MockRetrofit.Builder(builder).run {
            networkBehavior(behavior)
            build()
        }

        val delegate = mockRetrofit.create(CurrencyLayerService::class.java)
        return MockExampleService(delegate, context)
    }
    */

    private fun buildRetrofit(): Retrofit {
        val contentType = MediaType.parse("application/json")!!
        val json = Json.nonstrict
        return Retrofit.Builder().run {
            addConverterFactory(json.asConverterFactory(contentType))
            baseUrl(BuildConfig.CURRENCY_LAYER_API_URL)
            build()
        }
    }
}
