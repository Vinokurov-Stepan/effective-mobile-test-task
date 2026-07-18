package com.stepan_vin.coursesapp.feature.coursedetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseAppTheme
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseTypography
import com.stepan_vin.coursesapp.core.designsystem.theme.customColors
import com.stepan_vin.coursesapp.feature.coursedetails.R

@Composable
fun AuthorCard(
    authorName: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = stringResource(R.string.details_author_label),
                    style = CourseTypography.labelSmall.copy(
                        color = MaterialTheme.customColors.textHint
                    )
                )
                Text(
                    text = authorName,
                    style = CourseTypography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
    }
}

@Preview(
    name = "Author Card",
    showSystemUi = false
)
@Composable
private fun AuthorCardPreview() {
    CourseAppTheme {
        AuthorCard(
            authorName = "Merion Academy"
        )
    }
}
