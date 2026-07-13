package com.stepan_vin.coursesapp.core.database.di

import androidx.room.Room
import com.stepan_vin.coursesapp.core.common.Constants
import com.stepan_vin.coursesapp.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDatabase>().favoriteCourseDao()
    }
}
