package com.example.data.repository

import com.example.data.model.Rate

// TODO: Rename to RateRepository
interface LiveRepository {
    suspend fun getRates(currency: String): List<Rate>
}