package com.example.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Rate(
    val dist: String,
    val source: String,
    val rate: Float,
    val timestamp: Long
)

