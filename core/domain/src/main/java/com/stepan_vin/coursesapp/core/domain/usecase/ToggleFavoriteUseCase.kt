package com.stepan_vin.coursesapp.core.domain.usecase

import com.stepan_vin.coursesapp.core.domain.repository.CourseRepository

class ToggleFavoriteUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(courseId: Int) {
        repository.toggleFavorite(courseId)
    }
}
