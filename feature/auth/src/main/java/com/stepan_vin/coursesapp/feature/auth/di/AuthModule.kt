package com.stepan_vin.coursesapp.feature.auth.di

import com.stepan_vin.coursesapp.feature.auth.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel {
        LoginViewModel()
    }
}
