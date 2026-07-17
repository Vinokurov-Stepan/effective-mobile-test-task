package com.stepan_vin.coursesapp.feature.favorites.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stepan_vin.coursesapp.core.common.models.Course
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseAppTheme
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseTypography
import com.stepan_vin.coursesapp.core.designsystem.theme.customColors
import com.stepan_vin.coursesapp.feature.favorites.R
import com.stepan_vin.coursesapp.feature.main.components.CourseCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = koinViewModel(),
    onCourseClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoritesScreenContent(
        state = state,
        removeFromFavorites = { courseId ->
            viewModel.removeFromFavorites(courseId)
        },
        onCourseClick = onCourseClick
    )
}

@Composable
fun FavoritesScreenContent(
    state: FavoritesState,
    removeFromFavorites: (Int) -> Unit,
    onCourseClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.favorites_title),
            style = CourseTypography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(48.dp),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 4.dp
                    )
                }

                state.isError -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.favorites_error),
                            style = CourseTypography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSecondary,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }

                state.isEmpty -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.favorites_empty),
                            style = CourseTypography.titleMedium.copy(
                                color = MaterialTheme.customColors.textSecondary
                            )
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = state.courses,
                            key = { it.id }
                        ) { course ->
                            CourseCard(
                                course = course,
                                onFavoriteClick = {
                                    removeFromFavorites(course.id)
                                },
                                onDetailsClick = {
                                    onCourseClick(course.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Favorites Screen",
    showSystemUi = false
)
@Composable
private fun FavoritesScreenPreview() {
    CourseAppTheme {
        Surface(
            color = Color(0xFF131416),
            modifier = Modifier.fillMaxSize()
        ) {
            val mockCourses = listOf(
                Course(
                    id = 1,
                    title = "Java-разработчик с нуля",
                    text = "Освойте backend-разработку и программирование на Java, фреймворки...",
                    price = "999",
                    rate = 4.9,
                    startDate = "22 Мая 2024",
                    hasLike = true,
                    publishDate = "2024-05-22"
                ),
                Course(
                    id = 2,
                    title = "3D-дженералист",
                    text = "Освой профессию 3D-дженералиста и стань универсальным специалистом, который умеет...",
                    price = "12 000",
                    rate = 3.9,
                    startDate = "10 Сентября 2024",
                    hasLike = true,
                    publishDate = "2024-09-10"
                )
            )

            val previewState = FavoritesState(
                isLoading = false,
                courses = mockCourses,
                isEmpty = false,
                isError = false
            )

            FavoritesScreenContent(
                state = previewState,
                removeFromFavorites = {},
                onCourseClick = {},
            )
        }
    }
}

@Preview(
    name = "Favorites Screen (Loading)",
    showSystemUi = false
)
@Composable
private fun FavoritesScreenLoadingPreview() {
    CourseAppTheme {
        Surface(
            color = Color(0xFF131416),
            modifier = Modifier.fillMaxSize()
        ) {
            val previewState = FavoritesState(
                isLoading = true,
                courses = emptyList(),
                isEmpty = false,
                isError = false
            )

            FavoritesScreenContent(
                state = previewState,
                removeFromFavorites = {},
                onCourseClick = {},
            )
        }
    }
}

@Preview(
    name = "Favorites Screen (Error)",
    showSystemUi = false
)
@Composable
private fun FavoritesScreenErrorPreview() {
    CourseAppTheme {
        Surface(
            color = Color(0xFF131416),
            modifier = Modifier.fillMaxSize()
        ) {
            val previewState = FavoritesState(
                isLoading = false,
                courses = emptyList(),
                isEmpty = false,
                isError = true
            )

            FavoritesScreenContent(
                state = previewState,
                removeFromFavorites = {},
                onCourseClick = {},
            )
        }
    }
}

@Preview(
    name = "Favorites Screen (Empty)",
    showSystemUi = false
)
@Composable
private fun FavoritesScreenEmptyPreview() {
    CourseAppTheme {
        Surface(
            color = Color(0xFF131416),
            modifier = Modifier.fillMaxSize()
        ) {
            val previewState = FavoritesState(
                isLoading = false,
                courses = emptyList(),
                isEmpty = true,
                isError = false
            )

            FavoritesScreenContent(
                state = previewState,
                removeFromFavorites = {},
                onCourseClick = {},
            )
        }
    }
}
