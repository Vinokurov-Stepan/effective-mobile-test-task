package com.stepan_vin.coursesapp.core.network.mappers

import com.stepan_vin.coursesapp.core.common.models.Course
import com.stepan_vin.coursesapp.core.common.utils.formatDate
import com.stepan_vin.coursesapp.core.network.models.CourseDto

fun CourseDto.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = formatDate(startDate),
        hasLike = hasLike,
        publishDate = publishDate
    )
}

fun List<CourseDto>.toDomainList(): List<Course> {
    return this.map { it.toDomain() }
}
