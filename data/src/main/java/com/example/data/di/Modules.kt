package com.example.data.di

import com.example.data.db.CurrencyDatabase
import com.example.data.remote.CurrencyLayerClient
import com.example.data.repository.LiveRepository
import com.example.data.repository.LiveRepositoryImpl
import org.koin.dsl.module


val apiModule = module {
    single { CurrencyLayerClient.build() }
}

/*
val mockApiModule = module {
    single { CurrencyLayerClient.buildMock(get()) }
}
*/

val repositoryModule = module {
    single<LiveRepository> { LiveRepositoryImpl(get(), get(), get()) }
}

val databaseModule = module {
    single { CurrencyDatabase.getDatabase(get()) }
    single { get<CurrencyDatabase>().rateDao() }
}
