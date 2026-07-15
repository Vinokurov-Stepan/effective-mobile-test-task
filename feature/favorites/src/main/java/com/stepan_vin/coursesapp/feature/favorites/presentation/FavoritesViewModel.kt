package com.stepan_vin.coursesapp.feature.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stepan_vin.coursesapp.core.domain.usecase.GetFavoritesUseCase
import com.stepan_vin.coursesapp.core.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = _state.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase()
                .collectLatest { courses ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            courses = courses,
                            isEmpty = courses.isEmpty()
                        )
                    }
                }
        }
    }

    fun removeFromFavorites(courseId: Int) {
        viewModelScope.launch {
            toggleFavoriteUseCase(courseId)
        }
    }
}
