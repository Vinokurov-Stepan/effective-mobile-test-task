package com.stepan_vin.coursesapp.feature.main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.stepan_vin.coursesapp.core.common.models.Course
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseAppTheme
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseTypography
import com.stepan_vin.coursesapp.core.designsystem.theme.customColors
import com.stepan_vin.coursesapp.core.designsystem.theme.searchFieldColors
import com.stepan_vin.coursesapp.feature.main.R
import com.stepan_vin.coursesapp.feature.main.components.CourseCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    homeGraphEntry: NavBackStackEntry,
    viewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = homeGraphEntry
    ),
    onCourseClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    LaunchedEffect(state.isSortingEnabled) {
        if (state.courses.isNotEmpty()) {
            listState.scrollToItem(0)
        }
    }

    MainScreenContent(
        state = state,
        listState = listState,
        onSortToggle = {
            if (state.isSortingEnabled) {
                viewModel.resetSorting()
            } else {
                viewModel.sortByDate()
            }
        },
        onRetry = { viewModel.loadCourses() },
        onFavoriteClick = { courseId ->
            viewModel.toggleFavorite(courseId)
        },
        onCourseClick = onCourseClick
    )
}

@Composable
fun MainScreenContent(
    state: MainState,
    listState: LazyListState,
    onSortToggle: () -> Unit,
    onRetry: () -> Unit,
    onFavoriteClick: (Int) -> Unit,
    onCourseClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = stringResource(R.string.main_search_placeholder),
                        style = CourseTypography.titleMedium.copy(
                            color = MaterialTheme.customColors.textHint
                        )
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(20.dp)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .padding(end = 8.dp),
                enabled = false,
                singleLine = true,
                shape = RoundedCornerShape(28.dp),
                colors = searchFieldColors()
            )

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(56.dp),
                enabled = false
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(28.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter_icon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        if (state.isSortingEnabled) {
                            onSortToggle()
                        } else {
                            onSortToggle()
                        }
                    }
                    .padding(all = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (state.isSortingEnabled) {
                        stringResource(R.string.main_sort_default)
                    } else {
                        stringResource(R.string.main_sort_by_date)
                    },
                    style = CourseTypography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Icon(
                    painter = painterResource(
                        id = R.drawable.sorting_icon
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxSize()) {
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
                            text = stringResource(R.string.main_error),
                            style = CourseTypography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSecondary,
                                textAlign = TextAlign.Center
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextButton(
                            onClick = onRetry,
                            modifier = Modifier
                                .padding(horizontal = 0.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.main_retry),
                                style = CourseTypography.titleMedium.copy(
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }

                state.courses.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.main_empty),
                            style = CourseTypography.titleMedium.copy(
                                color = MaterialTheme.customColors.textSecondary
                            )
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = listState
                    ) {
                        items(
                            items = state.courses,
                            key = { it.id }
                        ) { course ->
                            CourseCard(
                                course = course,
                                onFavoriteClick = { onFavoriteClick(course.id) },
                                onDetailsClick = { onCourseClick(course.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Main Screen",
    showSystemUi = false
)
@Composable
private fun MainScreenPreview() {
    CourseAppTheme {
        val mockCourses = listOf(
            Course(
                id = 1,
                title = "Java-разработчик с нуля",
                text = "Освойте backend-разработку и программирование на Java, фреймворки...",
                price = "999",
                rate = 4.9,
                startDate = "22 Мая 2024",
                hasLike = false,
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

        val mockState = MainState(
            isLoading = false,
            courses = mockCourses,
            isError = false,
            isSortingEnabled = false,
            isDataLoaded = true
        )

        val listState = rememberLazyListState()

        MainScreenContent(
            state = mockState,
            listState = listState,
            onSortToggle = {},
            onRetry = {},
            onFavoriteClick = {},
            onCourseClick = {}
        )
    }
}

@Preview(
    name = "Main Screen (Loading State)",
    showSystemUi = false
)
@Composable
private fun MainScreenLoadingPreview() {
    CourseAppTheme {
        val mockState = MainState(
            isLoading = true,
            courses = emptyList(),
            isError = false,
            isSortingEnabled = false,
            isDataLoaded = false
        )

        val listState = rememberLazyListState()

        MainScreenContent(
            state = mockState,
            listState = listState,
            onSortToggle = {},
            onRetry = {},
            onFavoriteClick = {},
            onCourseClick = {}
        )
    }
}

@Preview(
    name = "Main Screen (Error State)",
    showSystemUi = false
)
@Composable
private fun MainScreenErrorPreview() {
    CourseAppTheme {
        val mockState = MainState(
            isLoading = false,
            courses = emptyList(),
            isError = true,
            isSortingEnabled = false,
            isDataLoaded = true
        )

        val listState = rememberLazyListState()

        MainScreenContent(
            state = mockState,
            listState = listState,
            onSortToggle = {},
            onRetry = {},
            onFavoriteClick = {},
            onCourseClick = {}
        )
    }
}

@Preview(
    name = "Main Screen (Empty State)",
    showSystemUi = false
)
@Composable
private fun MainScreenEmptyPreview() {
    CourseAppTheme {
        val mockState = MainState(
            isLoading = false,
            courses = emptyList(),
            isError = false,
            isSortingEnabled = false,
            isDataLoaded = true
        )

        val listState = rememberLazyListState()

        MainScreenContent(
            state = mockState,
            listState = listState,
            onSortToggle = {},
            onRetry = {},
            onFavoriteClick = {},
            onCourseClick = {}
        )
    }
}