package com.stepan_vin.coursesapp.feature.main.di

import com.stepan_vin.coursesapp.feature.main.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        MainViewModel(
            getCoursesUseCase = get(),
            toggleFavoriteUseCase = get()
        )
    }
}
