package com.stepan_vin.coursesapp.feature.favorites.di

import com.stepan_vin.coursesapp.feature.favorites.presentation.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel {
        FavoritesViewModel(
            getFavoritesUseCase = get(),
            toggleFavoriteUseCase = get()
        )
    }
}
