package com.stepan_vin.coursesapp.feature.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stepan_vin.coursesapp.core.domain.usecase.GetCoursesUseCase
import com.stepan_vin.coursesapp.core.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        loadCourses()
    }

    fun loadCourses() {
        if (_state.value.isDataLoaded) return
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val courses = getCoursesUseCase()
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        originalCourses = courses,
                        error = null,
                        isDataLoaded = true
                    ).withAppliedSorting()
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Ошибка загрузки курсов"
                    )
                }
            }
        }
    }

    fun sortByDate() {
        _state.update { it.copy(isSortingEnabled = true).withAppliedSorting() }
    }

    fun resetSorting() {
        _state.update { it.copy(isSortingEnabled = false).withAppliedSorting() }
    }

    fun toggleFavorite(courseId: Int) {
        viewModelScope.launch {
            toggleFavoriteUseCase(courseId)
            _state.update { currentState ->
                val updatedOriginal = currentState.originalCourses.map { course ->
                    if (course.id == courseId) {
                        course.copy(hasLike = !course.hasLike)
                    } else {
                        course
                    }
                }
                currentState.copy(originalCourses = updatedOriginal).withAppliedSorting()
            }
        }
    }

    private fun MainState.withAppliedSorting(): MainState {
        val sortedList = if (this.isSortingEnabled) {
            this.originalCourses.sortedByDescending { it.publishDate }
        } else {
            this.originalCourses
        }
        return this.copy(courses = sortedList)
    }
}
