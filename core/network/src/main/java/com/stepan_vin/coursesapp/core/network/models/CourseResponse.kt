package com.stepan_vin.coursesapp.core.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseResponse(
    @Json(name = "courses")
    val courses: List<CourseDto>
)
