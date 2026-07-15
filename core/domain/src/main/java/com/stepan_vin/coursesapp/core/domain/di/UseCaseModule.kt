package com.stepan_vin.coursesapp.core.domain.di

import com.stepan_vin.coursesapp.core.domain.usecase.GetCoursesUseCase
import com.stepan_vin.coursesapp.core.domain.usecase.GetFavoritesUseCase
import com.stepan_vin.coursesapp.core.domain.usecase.ToggleFavoriteUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        GetCoursesUseCase(
            repository = get()
        )
    }

    factory {
        ToggleFavoriteUseCase(
            repository = get()
        )
    }

    factory {
        GetFavoritesUseCase(
            repository = get()
        )
    }
}
