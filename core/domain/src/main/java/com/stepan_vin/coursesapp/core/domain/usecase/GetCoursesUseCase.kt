package com.stepan_vin.coursesapp.core.domain.usecase

import com.stepan_vin.coursesapp.core.common.models.Course
import com.stepan_vin.coursesapp.core.domain.repository.CourseRepository

class GetCoursesUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(): List<Course> {
        return repository.getCourses()
    }
}
