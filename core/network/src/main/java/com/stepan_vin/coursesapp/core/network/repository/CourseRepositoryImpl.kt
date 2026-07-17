package com.stepan_vin.coursesapp.core.network.repository

import com.stepan_vin.coursesapp.core.common.models.Course
import com.stepan_vin.coursesapp.core.database.dao.FavoriteCourseDao
import com.stepan_vin.coursesapp.core.database.entities.FavoriteCourseEntity
import com.stepan_vin.coursesapp.core.domain.repository.CourseRepository
import com.stepan_vin.coursesapp.core.network.api.CourseApi
import com.stepan_vin.coursesapp.core.network.mappers.toDomainList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CourseRepositoryImpl(
    private val api: CourseApi,
    private val dao: FavoriteCourseDao
) : CourseRepository {

    override suspend fun getCourses(): List<Course> {
        val response = api.getCourses()
        val courses = response.courses.toDomainList()

        return courses.map { course ->
            course.copy(hasLike = dao.isFavorite(course.id))
        }
    }

    override suspend fun toggleFavorite(courseId: Int) {
        val course = getCourses().find { it.id == courseId }
        course?.let {
            if (it.hasLike) {
                dao.delete(
                    FavoriteCourseEntity(
                        id = it.id,
                        title = it.title,
                        price = it.price,
                        rate = it.rate,
                        startDate = it.startDate,
                        publishDate = it.publishDate
                    )
                )
            } else {
                dao.insert(
                    FavoriteCourseEntity(
                        id = it.id,
                        title = it.title,
                        price = it.price,
                        rate = it.rate,
                        startDate = it.startDate,
                        publishDate = it.publishDate
                    )
                )
            }
        }
    }

    override fun getFavorites(): Flow<List<Course>> {
        return dao.getAll().map { entities ->
            entities.map { entity ->
                Course(
                    id = entity.id,
                    title = entity.title,
                    text = "",
                    price = entity.price,
                    rate = entity.rate,
                    startDate = entity.startDate,
                    hasLike = true,
                    publishDate = entity.publishDate
                )
            }
        }
    }

    override fun getFavoriteIds(): Flow<List<Int>> {
        return dao.getAllIds()
    }

    override suspend fun isFavorite(courseId: Int): Boolean {
        return dao.isFavorite(courseId)
    }
}
