package com.example.data.model

import androidx.room.Ignore
import kotlinx.serialization.Serializable


@Serializable
data class Live(
    val success: Boolean,
    val timestamp: Long,
    val source: String,
    @Ignore
    val quotes: Map<String, Float>
)

