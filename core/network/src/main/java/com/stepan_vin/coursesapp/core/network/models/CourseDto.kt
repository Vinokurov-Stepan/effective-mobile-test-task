package com.stepan_vin.coursesapp.core.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseDto(
    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "text")
    val text: String,

    @Json(name = "price")
    val price: String,

    @Json(name = "rate")
    val rate: Double,

    @Json(name = "startDate")
    val startDate: String,

    @Json(name = "hasLike")
    val hasLike: Boolean,

    @Json(name = "publishDate")
    val publishDate: String
)
