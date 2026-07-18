package com.stepan_vin.coursesapp.feature.coursedetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stepan_vin.coursesapp.core.common.models.Course
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseAppTheme
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseTypography
import com.stepan_vin.coursesapp.core.designsystem.theme.customColors
import com.stepan_vin.coursesapp.feature.coursedetails.R
import com.stepan_vin.coursesapp.feature.coursedetails.components.AuthorCard
import com.stepan_vin.coursesapp.feature.coursedetails.components.CourseDetailsBanner
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CourseDetailsScreen(
    courseId: Int,
    onBack: () -> Unit
) {
    val viewModel: CourseDetailsViewModel = koinViewModel(
        parameters = { parametersOf(courseId) }
    )
    val state by viewModel.state.collectAsStateWithLifecycle()

    CourseDetailsScreenContent(
        state = state,
        onBack = onBack,
        onToggleFavorite = { viewModel.toggleFavorite() }
    )
}

@Composable
fun CourseDetailsScreenContent(
    state: CourseDetailsState,
    onBack: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 4.dp
                    )
                }
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
                        text = stringResource(R.string.course_error),
                        style = CourseTypography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSecondary,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            state.course != null -> {
                val course = state.course

                CourseDetailsBanner(
                    imageResId = R.drawable.course_image,
                    rating = "${course.rate}",
                    date = course.startDate,
                    isFavorite = state.isFavorite,
                    onBackClick = onBack,
                    onFavoriteClick = onToggleFavorite
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = course.title,
                        style = CourseTypography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                        modifier = Modifier.padding(top = 16.dp, bottom = 20.dp)
                    )

                    AuthorCard(
                        authorName = "Merion Academy",
                        modifier = Modifier.padding(bottom = 32.dp)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.details_start_course),
                                style = CourseTypography.labelMedium.copy(
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.details_go_to_platform),
                                style = CourseTypography.labelMedium.copy(
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = stringResource(R.string.details_about_title),
                        style = CourseTypography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Text(
                        text = course.text,
                        style = CourseTypography.bodyMedium.copy(
                            color = MaterialTheme.customColors.textHint
                        )
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Course Details Screen",
    showSystemUi = false
)
@Composable
private fun CourseDetailsScreenPreview() {
    CourseAppTheme {
        val mockCourse = Course(
            id = 1,
            title = "Java-разработчик с нуля",
            text = "У вас будет 7 видеоуроков в высоком качестве. На них спикер объясняет теорию и показывает как выполнять практические задания. Доступ к материалам сохраняется на 2 года.\n\nКроме теоретических материалов вас ждут тесты и практические задания. Они помогут лучше запомнить новую информацию.",
            price = "999",
            rate = 4.9,
            startDate = "22 Мая 2024",
            hasLike = false,
            publishDate = "2024-05-22"
        )

        val mockState = CourseDetailsState(
            isLoading = false,
            course = mockCourse,
            isFavorite = false,
            isError = false
        )

        CourseDetailsScreenContent(
            state = mockState,
            onBack = {},
            onToggleFavorite = {}
        )
    }
}
