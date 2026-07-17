package com.stepan_vin.coursesapp.feature.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseColors

@Composable
fun ProfileCourseCard(
    title: String,
    rating: String,
    date: String,
    progress: Float,
    progressText: String,
    lessons: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = CourseColors.TextSecondary)
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
                    text = rating,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = CourseColors.Green,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = date,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = CourseColors.TextSecondary
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = CourseColors.TextPrimary,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(CourseColors.Surface)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(CourseColors.Green)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = progressText,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = CourseColors.TextPrimary,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = lessons,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = CourseColors.TextSecondary
                )
            )
        }
    }
}
