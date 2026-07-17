package com.stepan_vin.coursesapp.feature.favorites.presentation

import com.stepan_vin.coursesapp.core.common.models.Course

data class FavoritesState(
    val isLoading: Boolean = true,
    val courses: List<Course> = emptyList(),
    val isEmpty: Boolean = false,
    val isError: Boolean = false
)
