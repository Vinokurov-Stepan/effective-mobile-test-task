package com.stepan_vin.coursesapp.di

import com.stepan_vin.coursesapp.core.database.di.databaseModule
import com.stepan_vin.coursesapp.core.network.di.networkModule
import com.stepan_vin.coursesapp.core.network.di.repositoryModule
import com.stepan_vin.coursesapp.feature.auth.di.authModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration? = null) {

    startKoin {
        appDeclaration?.invoke(this)
        modules(
            networkModule,
            databaseModule,
            repositoryModule,
            authModule
        )
    }
}
