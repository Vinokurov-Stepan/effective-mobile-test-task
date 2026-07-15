package com.stepan_vin.coursesapp.di

import android.app.Application
import com.stepan_vin.coursesapp.core.database.di.databaseModule
import com.stepan_vin.coursesapp.core.domain.di.useCaseModule
import com.stepan_vin.coursesapp.core.network.di.networkModule
import com.stepan_vin.coursesapp.core.network.di.repositoryModule
import com.stepan_vin.coursesapp.feature.auth.di.authModule
import com.stepan_vin.coursesapp.feature.favorites.di.favoritesModule
import com.stepan_vin.coursesapp.feature.main.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(application: Application) {
    startKoin {
        androidContext(application)

        modules(
            networkModule,
            databaseModule,
            repositoryModule,
            useCaseModule,
            authModule,
            mainModule,
            favoritesModule
        )
    }
}
