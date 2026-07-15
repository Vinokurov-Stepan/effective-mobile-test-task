package com.stepan_vin.coursesapp.feature.coursedetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stepan_vin.coursesapp.core.common.models.Course
import com.stepan_vin.coursesapp.core.domain.repository.CourseRepository
import com.stepan_vin.coursesapp.core.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseDetailsViewModel(
    private val courseId: Int,
    private val repository: CourseRepository,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CourseDetailsState())
    val state: StateFlow<CourseDetailsState> = _state.asStateFlow()

    init {
        loadCourseDetails()
    }

    fun loadCourseDetails() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val allCourses = repository.getCourses()
                val course = allCourses.find { it.id == courseId }
                if (course != null) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            course = course,
                            isFavorite = course.hasLike,
                            error = null
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "Курс не найден"
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Ошибка загрузки курса"
                    )
                }
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            toggleFavoriteUseCase(courseId)
            _state.update { state ->
                val updatedCourse = state.course?.copy(hasLike = !state.isFavorite)
                state.copy(
                    course = updatedCourse,
                    isFavorite = !state.isFavorite
                )
            }
        }
    }

    @Suppress("unused")
    private suspend fun getCourseById(id: Int): Course? {
        val allCourses = repository.getCourses()
        return allCourses.find { it.id == id }
    }
}
