package com.stepan_vin.coursesapp.feature.profile.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseAppTheme
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseTypography
import com.stepan_vin.coursesapp.core.designsystem.theme.customColors
import com.stepan_vin.coursesapp.feature.main.R

@Composable
fun ProfileCourseCard(
    title: String,
    rating: String,
    date: String,
    progress: Float,
    completedLessons: String,
    lessons: String,
    isFavorite: Boolean = true,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(114.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 12.dp,
                            bottomEnd = 12.dp
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.course_image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 8.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.customColors.bookmarkBackground)
                        .clickable {},
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(
                            if (isFavorite) R.drawable.ic_favorite_border
                            else R.drawable.ic_favorite_filled
                        ),
                        contentDescription = null,
                        tint = if (isFavorite) MaterialTheme.colorScheme.primary else Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.customColors.bookmarkBackground,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.star_icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(12.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = rating,
                            style = CourseTypography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.customColors.bookmarkBackground,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = date,
                            style = CourseTypography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = CourseTypography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        style = CourseTypography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )

                    Row {
                        Text(
                            text = completedLessons,
                            style = CourseTypography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(
                            text = lessons,
                            style = CourseTypography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.customColors.textFieldBackground,
                    strokeCap = StrokeCap.Round
                )
            }
        }
    }
}

@Preview(
    name = "Profile Course Card (Favorite)",
    showSystemUi = false
)
@Composable
private fun ProfileCourseCardFavoritePreview() {
    CourseAppTheme {
        Surface(
            color = Color(0xFF131416),
            modifier = Modifier.padding(16.dp)
        ) {
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
    }
}

@Preview(
    name = "Profile Course Card (Not Favorite)",
    showSystemUi = false
)
@Composable
private fun ProfileCourseCardNotFavoritePreview() {
    CourseAppTheme {
        Surface(
            color = Color(0xFF131416),
            modifier = Modifier.padding(16.dp)
        ) {
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
