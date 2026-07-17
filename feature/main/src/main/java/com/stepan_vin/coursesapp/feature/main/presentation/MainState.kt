package com.stepan_vin.coursesapp.feature.main.presentation

import com.stepan_vin.coursesapp.core.common.models.Course

data class MainState(
    val isLoading: Boolean = false,
    val courses: List<Course> = emptyList(),
    val originalCourses: List<Course> = emptyList(),
    val isError: Boolean = false,
    val isSortingEnabled: Boolean = false,
    val isDataLoaded: Boolean = false
)
