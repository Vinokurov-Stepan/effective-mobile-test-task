package com.stepan_vin.coursesapp.feature.coursedetails.di

import com.stepan_vin.coursesapp.feature.coursedetails.presentation.CourseDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val courseDetailsModule = module {
    viewModel { parameters ->
        CourseDetailsViewModel(
            courseId = parameters.get(),
            repository = get(),
            toggleFavoriteUseCase = get()
        )
    }
}
