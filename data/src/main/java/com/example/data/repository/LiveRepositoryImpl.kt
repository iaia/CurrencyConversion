package com.example.data.repository

import com.example.data.db.dao.LiveDao
import com.example.data.db.dao.RateDao
import com.example.data.model.Rate
import com.example.data.remote.CurrencyLayerService
import com.example.data.remote.paramsCreator

class LiveRepositoryImpl(
    private val liveDao: LiveDao,
    private val rateDao: RateDao,
    private val service: CurrencyLayerService
) : LiveRepository {
    override suspend fun getRates(currency: String?): List<Rate> {
        liveDao.getRecent("USD", getCurrentTimestamp() - (30 * 60)).apply {
            if (this == null) {
                fetchLive(currency)
            }
            return rateDao.getAll()
        }
    }

    private suspend fun fetchLive(currency: String?) {
        // 引数がnullのときはliveのrecentからsourceを取り出して、それをparamsにセットするみたいな処理すればいい
        // service.getLive(paramsCreator(mapOf("source" to currency))).body().apply {
        service.getLive(paramsCreator()).body().apply {
            if (this == null) {
                // do something
            } else {
                liveDao.insert(this)
                rateDao.insert(this.quotes.map {
                    Rate(
                        it.key.removePrefix(this.source),
                        this.source,
                        it.value,
                        0F,
                        this.timestamp
                    )
                })
            }
        }
    }

    private fun getCurrentTimestamp() = System.currentTimeMillis() / 1000
}