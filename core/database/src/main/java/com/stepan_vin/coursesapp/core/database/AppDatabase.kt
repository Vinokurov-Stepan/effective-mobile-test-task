package com.stepan_vin.coursesapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stepan_vin.coursesapp.core.database.dao.FavoriteCourseDao
import com.stepan_vin.coursesapp.core.database.entities.FavoriteCourseEntity

@Database(
    entities = [FavoriteCourseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteCourseDao(): FavoriteCourseDao
}
