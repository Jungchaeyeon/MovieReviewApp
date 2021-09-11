package com.jcy.moviereviewapp.di

import android.app.Application
import com.jcy.moviereviewapp.BuildConfig
import com.jcy.moviereviewapp.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.ERROR)
            } else {
                androidLogger(Level.INFO)
            }

            androidContext(this@KoinApplication)

            modules(listOf(
                repositoryModule,
                remoteDataModule,
                viewModelModule,
                apiModule))

        }
    }
}