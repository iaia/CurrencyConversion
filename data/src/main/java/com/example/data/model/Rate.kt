package com.example.data.model

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity(primaryKeys = ["dist", "source"])
data class Rate(
    var dist: String,
    var source: String,
    var rate: Float,
    var timestamp: Long
)

