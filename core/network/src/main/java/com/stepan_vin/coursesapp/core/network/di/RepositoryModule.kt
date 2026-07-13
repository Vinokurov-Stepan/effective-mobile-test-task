package com.stepan_vin.coursesapp.core.network.di

import com.stepan_vin.coursesapp.core.domain.repository.CourseRepository
import com.stepan_vin.coursesapp.core.network.repository.CourseRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<CourseRepository> {
        CourseRepositoryImpl(
            api = get(),
            dao = get()
        )
    }
}
