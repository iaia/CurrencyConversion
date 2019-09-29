package com.example.data.repository

import com.example.data.remote.CurrencyLayerService

class LiveRepositoryImpl(
    private val service: CurrencyLayerService
) : LiveRepository {
    override suspend fun getLive(): String {
        service.getLive().body().apply {
            return this ?: "failed"
        }
    }
}