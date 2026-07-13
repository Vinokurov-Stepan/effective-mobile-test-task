package com.stepan_vin.coursesapp.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteCourseEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: String,
    val rate: Double,
    val startDate: String,
    val publishDate: String
)
