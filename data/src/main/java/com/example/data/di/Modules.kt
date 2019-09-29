package com.example.data.di

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
    single<LiveRepository> { LiveRepositoryImpl(get()) }
}

/*
val databaseModule = module {
    single { DummyDatabase.getDatabase(get()) }
    single { get<DummyDatabase>().employeeDao() }
}
*/