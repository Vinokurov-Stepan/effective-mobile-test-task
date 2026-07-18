package com.stepan_vin.coursesapp.feature.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseAppTheme
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseTypography
import com.stepan_vin.coursesapp.core.designsystem.theme.customColors
import com.stepan_vin.coursesapp.feature.profile.R
import com.stepan_vin.coursesapp.feature.profile.components.ProfileCourseCard
import com.stepan_vin.coursesapp.feature.profile.components.ProfileMenuItem

@Composable
fun ProfileScreen() {
    ProfileScreenContent()
}

@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.profile_title),
            style = CourseTypography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Card(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileMenuItem(
                    title = stringResource(R.string.profile_support),
                    onClick = {}
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.customColors.divider,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                ProfileMenuItem(
                    title = stringResource(R.string.profile_settings),
                    onClick = {}
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.customColors.divider,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                ProfileMenuItem(
                    title = stringResource(R.string.profile_logout),
                    onClick = {}
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.profile_my_courses),
            style = CourseTypography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ProfileCourseCard(
                    title = "3D-дженералист",
                    rating = "3.9",
                    date = "10 Сентября 2024",
                    progress = 0.5f,
                    completedLessons = "22",
                    lessons = "/44 уроков",
                    isFavorite = true
                )
            }
            item {
                ProfileCourseCard(
                    title = "Java-разработчик с нуля",
                    rating = "4.9",
                    date = "29 мая 2024",
                    progress = 0.3f,
                    completedLessons = "15",
                    lessons = "/48 уроков",
                    isFavorite = false
                )
            }
        }
    }
}

@Preview(
    name = "Profile Screen",
    showSystemUi = false
)
@Composable
private fun ProfileScreenPreview() {
    CourseAppTheme {
        ProfileScreenContent()
    }
}
