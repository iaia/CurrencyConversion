package com.example.currencyconversion

import android.app.Application
import com.example.currencyconversion.di.viewModelModule
import com.example.data.di.apiModule
import com.example.data.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    apiModule,
                    repositoryModule
                    //fragmentViewModelModule,
                    //mockApiModule,
                    //databaseModule
                )
            )
        }
    }
}