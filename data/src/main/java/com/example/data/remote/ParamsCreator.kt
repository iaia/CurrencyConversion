package com.example.data.remote

import com.example.data.BuildConfig

fun paramsCreator(params: Map<String, String> = emptyMap()) = mapOf(
    "access_key" to BuildConfig.CURRENCY_LAYER_API_KEY,
    "format" to "1"
).plus(params)
