package com.example.data.repository

import com.example.data.model.Rate

interface LiveRepository {
    suspend fun getLive(): List<Rate>
}