package com.example.data.model

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Entity(primaryKeys = ["code", "name"])
data class Currency(
    var code: String,
    var name: String
) {
    constructor() : this("", "")
}

@Serializable
data class CurrenciesResult(
    var currencies: Map<String, String>
)
