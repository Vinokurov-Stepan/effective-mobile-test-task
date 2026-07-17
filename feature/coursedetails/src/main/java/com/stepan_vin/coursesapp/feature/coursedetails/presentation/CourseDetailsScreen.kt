package com.stepan_vin.coursesapp.feature.coursedetails.presentation

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseColors
import com.stepan_vin.coursesapp.feature.coursedetails.R
import com.stepan_vin.coursesapp.feature.coursedetails.components.AuthorCard
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = stringResource(R.string.details_back),
                    tint = CourseColors.TextPrimary
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            state.course?.let { _ ->
                Icon(
                    painter = painterResource(
                        id = if (state.isFavorite) R.drawable.isfavourite else R.drawable.notfavourite
                    ),
                    contentDescription = if (state.isFavorite) {
                        stringResource(R.string.details_favorite_remove)
                    } else {
                        stringResource(R.string.details_favorite_add)
                    },
                    tint = if (state.isFavorite) CourseColors.Green else CourseColors.TextSecondary,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { viewModel.toggleFavorite() }
                )
            }
        }

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(400.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = CourseColors.Green,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            state.error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ошибка: ${state.error}",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = CourseColors.TextSecondary
                        )
                    )
                }
            }

            state.course != null -> {
                val course = state.course!!
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${course.rate}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = CourseColors.Green,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = course.startDate,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = CourseColors.TextSecondary
                        )
                    )
                }

                Text(
                    text = course.title,
                    style = TextStyle(
                        fontSize = 28.sp,
                        color = CourseColors.TextPrimary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                AuthorCard(
                    authorName = "Merion Academy",
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CourseColors.TextSecondary
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(stringResource(R.string.details_start_course))
                    }

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CourseColors.Surface,
                            contentColor = CourseColors.TextSecondary
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(stringResource(R.string.details_go_to_platform))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(containerColor = CourseColors.TextSecondary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.details_about_title),
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = CourseColors.TextPrimary,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = course.text,
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = CourseColors.TextSecondary,
                                lineHeight = 22.sp
                            )
                        )
                    }
                }
            }
        }
    }
}
