package com.example.testtechniquelydia.core

import android.app.Application
import com.example.testtechniquelydia.core.di.coreModule
import com.example.testtechniquelydia.core.di.dataModule
import com.example.testtechniquelydia.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ContactApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ContactApplication)
            modules(coreModule, dataModule, viewModelModule)
        }
    }
}