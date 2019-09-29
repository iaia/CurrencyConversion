package com.example.data.repository

interface LiveRepository {
    suspend fun getLive(): String
}