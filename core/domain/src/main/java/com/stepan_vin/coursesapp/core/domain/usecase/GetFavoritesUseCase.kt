package com.stepan_vin.coursesapp.core.domain.usecase

import com.stepan_vin.coursesapp.core.common.models.Course
import com.stepan_vin.coursesapp.core.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(
    private val repository: CourseRepository
) {
    operator fun invoke(): Flow<List<Course>> {
        return repository.getFavorites()
    }
}
