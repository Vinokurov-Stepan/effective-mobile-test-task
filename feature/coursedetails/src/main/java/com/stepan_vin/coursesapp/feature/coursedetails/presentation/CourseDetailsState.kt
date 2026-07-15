package com.stepan_vin.coursesapp.feature.coursedetails.presentation

import com.stepan_vin.coursesapp.core.common.models.Course

data class CourseDetailsState(
    val isLoading: Boolean = true,
    val course: Course? = null,
    val isFavorite: Boolean = false,
    val error: String? = null
)
