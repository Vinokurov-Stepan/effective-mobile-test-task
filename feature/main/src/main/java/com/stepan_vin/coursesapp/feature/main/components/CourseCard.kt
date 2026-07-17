package com.stepan_vin.coursesapp.feature.main.components

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stepan_vin.coursesapp.core.common.models.Course
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseAppTheme
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseTypography
import com.stepan_vin.coursesapp.core.designsystem.theme.customColors
import com.stepan_vin.coursesapp.feature.main.R

@Composable
fun CourseCard(
    course: Course,
    onFavoriteClick: () -> Unit,
    onDetailsClick: () -> Unit,
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
                        .clickable { onFavoriteClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (course.hasLike) R.drawable.ic_favorite_border else R.drawable.ic_favorite_filled
                        ),
                        contentDescription = null,
                        tint = if (course.hasLike) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onPrimary,
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
                            text = "${course.rate}",
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
                            text = course.startDate,
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
                    text = course.title,
                    style = CourseTypography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = course.text,
                    style = CourseTypography.headlineSmall.copy(
                        color = MaterialTheme.customColors.textHint
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${course.price} ₽",
                        style = CourseTypography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                    )

                    Row(
                        modifier = Modifier.clickable { onDetailsClick() },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.main_details_button),
                            style = CourseTypography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right_icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Course Card",
    showSystemUi = false
)
@Composable
private fun CourseCardPreview() {
    CourseAppTheme {
        Surface(
            color = Color(0xFF151515),
            modifier = Modifier.padding(16.dp)
        ) {
            CourseCard(
                course = Course(
                    id = 1,
                    title = "Java-разработчик с нуля",
                    text = "Освойте backend-разработку и программирование на Java, фреймворки...",
                    price = "999",
                    rate = 4.9,
                    startDate = "22 Мая 2024",
                    hasLike = false,
                    publishDate = "2024-05-22"
                ),
                onFavoriteClick = {},
                onDetailsClick = {}
            )
        }
    }
}

@Preview(
    name = "Course Card (Liked)",
    showSystemUi = false
)
@Composable
private fun CourseCardLikedPreview() {
    CourseAppTheme {
        Surface(
            color = Color(0xFF151515),
            modifier = Modifier.padding(16.dp)
        ) {
            CourseCard(
                course = Course(
                    id = 2,
                    title = "3D-дженералист",
                    text = "Освой профессию 3D-дженералиста и стань универсальным специалистом, который умеет...",
                    price = "12 000",
                    rate = 3.9,
                    startDate = "10 Сентября 2024",
                    hasLike = true,
                    publishDate = "2024-09-10"
                ),
                onFavoriteClick = {},
                onDetailsClick = {}
            )
        }
    }
}
