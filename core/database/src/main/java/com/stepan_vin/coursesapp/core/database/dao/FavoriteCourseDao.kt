package com.stepan_vin.coursesapp.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stepan_vin.coursesapp.core.database.entities.FavoriteCourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: FavoriteCourseEntity)

    @Delete
    suspend fun delete(course: FavoriteCourseEntity)

    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<FavoriteCourseEntity>>

    @Query("SELECT id FROM favorites")
    fun getAllIds(): Flow<List<Int>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :courseId)")
    suspend fun isFavorite(courseId: Int): Boolean
}
