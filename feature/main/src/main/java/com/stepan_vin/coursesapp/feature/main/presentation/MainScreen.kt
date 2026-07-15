package com.stepan_vin.coursesapp.feature.main.presentation

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseColors
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseTypography
import com.stepan_vin.coursesapp.feature.main.R
import com.stepan_vin.coursesapp.feature.main.components.CourseCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    homeGraphEntry: NavBackStackEntry,
    viewModel: MainViewModel = koinViewModel(viewModelStoreOwner = homeGraphEntry),
    onCourseClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    LaunchedEffect(state.isSortingEnabled) {
        if (state.courses.isNotEmpty()) {
            listState.scrollToItem(0)
        }
    }

    Column(
        modifier = Modifier
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
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White.copy(alpha = 0.6f)
                        )
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "Поиск",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .padding(end = 12.dp),
                enabled = false,
                singleLine = true,
                shape = RoundedCornerShape(28.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF24252A),
                    unfocusedContainerColor = Color(0xFF24252A),
                    disabledContainerColor = Color(0xFF24252A),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.White,
                    focusedPlaceholderColor = Color.White.copy(alpha = 0.6f),
                    unfocusedPlaceholderColor = Color.White.copy(alpha = 0.6f),
                    disabledPlaceholderColor = Color.White.copy(alpha = 0.6f)
                )
            )

            IconButton(
                onClick = {},
                modifier = Modifier.size(56.dp),
                enabled = false
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter_icon),
                        contentDescription = "Фильтр",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    if (state.isSortingEnabled) {
                        viewModel.resetSorting()
                    } else {
                        viewModel.sortByDate()
                    }
                },
                modifier = Modifier.padding(0.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (state.isSortingEnabled) {
                            stringResource(R.string.main_sort_default)
                        } else {
                            stringResource(R.string.main_sort_by_date)
                        },
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = CourseColors.Primary,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(R.drawable.sorting_icon),
                        contentDescription = if (state.isSortingEnabled) "Сортировка по умолчанию" else "Сортировка по дате",
                        tint = CourseColors.Primary,
                        modifier = Modifier.size(16.dp)
                    )
                }
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
                        color = CourseColors.Green,
                        strokeWidth = 4.dp
                    )
                }

                state.error != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${stringResource(R.string.main_error)}: ${state.error}",
                            style = CourseTypography.bodyMedium.copy(color = CourseColors.Error)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(onClick = { viewModel.loadCourses() }) {
                            Text(stringResource(R.string.main_retry))
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
                            style = CourseTypography.bodyMedium.copy(color = CourseColors.TextSecondary)
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
                                onFavoriteClick = { viewModel.toggleFavorite(course.id) },
                                onDetailsClick = { onCourseClick(course.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
