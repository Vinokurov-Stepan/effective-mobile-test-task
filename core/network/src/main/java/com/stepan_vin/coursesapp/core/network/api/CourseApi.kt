package com.stepan_vin.coursesapp.core.network.api

import com.stepan_vin.coursesapp.core.network.models.CourseResponse
import retrofit2.http.GET

interface CourseApi {
    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): CourseResponse
}
