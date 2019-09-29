package com.example.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Live(
    val success: Boolean,
    val timestamp: Int,
    val source: String,
    val quotes: Map<String, Float>
)

