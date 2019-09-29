package com.example.currencyconversion.feature.rate

import com.airbnb.epoxy.TypedEpoxyController
import com.example.currencyconversion.itemRate
import com.example.data.model.Rate

class RateController: TypedEpoxyController<List<Rate>>() {
    override fun buildModels(rates: List<Rate>) {
        rates.forEach {
            itemRate {
                id(it.dist)
                dist(it.dist)
                rate(it.rate.toString())
                culcResult(it.calcResult.toString())
            }
        }
    }
}