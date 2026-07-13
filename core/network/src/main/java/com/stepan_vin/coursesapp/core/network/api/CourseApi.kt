package com.stepan_vin.coursesapp.core.network.api

import com.stepan_vin.coursesapp.core.network.models.CourseDto
import retrofit2.http.GET

interface CourseApi {
    @GET("courses")
    suspend fun getCourses(): List<CourseDto>
}
