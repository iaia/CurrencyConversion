package com.example.data.model

import androidx.room.Entity
import androidx.room.Ignore
import kotlinx.serialization.Serializable

@Serializable
@Entity(primaryKeys = ["dist", "source"])
data class Rate(
    var dist: String,
    var source: String,
    var rate: Float,
    @Ignore
    var calcResult: Float,
    var timestamp: Long
) {
    constructor() : this("", "", 0F, 0F, 0L)
}
