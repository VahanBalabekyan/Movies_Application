package com.movies.test

import android.app.Application
import com.movies.test.shared.modules.netModule
import com.movies.test.shared.modules.repositoryModule
import com.movies.test.shared.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(netModule, viewModelModule,repositoryModule)
        }
    }
}