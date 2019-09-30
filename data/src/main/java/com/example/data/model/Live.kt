package com.example.data.model

import androidx.room.Entity
import androidx.room.Ignore
import kotlinx.serialization.Serializable

@Serializable
@Entity(primaryKeys = ["timestamp", "source"])
data class Live(
    var success: Boolean,
    var timestamp: Long,
    var source: String,
    @Ignore
    var quotes: Map<String, Float>
) {
    constructor() : this(false, 0, "", mapOf())
}
