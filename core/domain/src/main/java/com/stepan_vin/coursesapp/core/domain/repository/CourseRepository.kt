package com.stepan_vin.coursesapp.core.domain.repository

import com.stepan_vin.coursesapp.core.common.models.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    suspend fun getCourses(): List<Course>
    suspend fun toggleFavorite(courseId: Int)
    fun getFavorites(): Flow<List<Course>>
    suspend fun isFavorite(courseId: Int): Boolean
    fun getFavoriteIds(): Flow<List<Int>>
}
