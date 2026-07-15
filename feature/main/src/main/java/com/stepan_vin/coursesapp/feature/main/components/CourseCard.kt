package com.stepan_vin.coursesapp.feature.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stepan_vin.coursesapp.core.common.models.Course
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseColors
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
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = CourseColors.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${course.rate}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = CourseColors.Green,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = course.startDate,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = CourseColors.TextSecondary
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(
                        id = if (course.hasLike) R.drawable.notfavourite else R.drawable.isfavourite
                    ),
                    contentDescription = if (course.hasLike) {
                        stringResource(R.string.main_favorite_content_description_remove)
                    } else {
                        stringResource(R.string.main_favorite_content_description_add)
                    },
                    tint = if (course.hasLike) CourseColors.Green else CourseColors.TextSecondary,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onFavoriteClick() }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = course.title,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = CourseColors.TextPrimary,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = course.text,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = CourseColors.TextSecondary
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${course.price} ₽",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = CourseColors.TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = stringResource(R.string.main_details_button),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = CourseColors.Primary,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.clickable { onDetailsClick() }
                )
            }
        }
    }
}
