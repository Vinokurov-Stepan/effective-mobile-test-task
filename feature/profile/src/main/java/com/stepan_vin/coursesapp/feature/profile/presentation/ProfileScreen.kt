package com.stepan_vin.coursesapp.feature.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseColors
import com.stepan_vin.coursesapp.feature.profile.R
import com.stepan_vin.coursesapp.feature.profile.components.ProfileCourseCard

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.profile_title),
            style = TextStyle(
                fontSize = 24.sp,
                color = CourseColors.TextPrimary,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        ProfileMenuItem(
            title = stringResource(R.string.profile_support),
            onClick = {}
        )

        Divider(
            color = CourseColors.Surface,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        ProfileMenuItem(
            title = stringResource(R.string.profile_settings),
            onClick = {}
        )

        Divider(
            color = CourseColors.Surface,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        ProfileMenuItem(
            title = stringResource(R.string.profile_logout),
            onClick = {},
            textColor = CourseColors.Error
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.profile_my_courses),
            style = TextStyle(
                fontSize = 18.sp,
                color = CourseColors.TextPrimary,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ProfileCourseCard(
            title = "3D-дженералист",
            rating = "3.9",
            date = "10 Сентября 2024",
            progress = 0.5f,
            progressText = "50%",
            lessons = "22/44 уроков"
        )

        ProfileCourseCard(
            title = "Java-разработчик с нуля",
            rating = "4.9",
            date = "29 мая 2024",
            progress = 0.3f,
            progressText = "30%",
            lessons = "15/48 уроков"
        )
    }
}

@Composable
private fun ProfileMenuItem(
    title: String,
    onClick: () -> Unit,
    textColor: androidx.compose.ui.graphics.Color = CourseColors.TextPrimary
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                color = textColor,
                fontWeight = FontWeight.Medium
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = R.drawable.chevron_right),
            contentDescription = "Перейти",
            tint = CourseColors.TextHint,
            modifier = Modifier.width(20.dp)
        )
    }
}
