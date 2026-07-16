package com.stepan_vin.coursesapp.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = CourseColors.Primary,
    onPrimary = CourseColors.White,
    primaryContainer = CourseColors.PrimaryLight,
    onPrimaryContainer = CourseColors.PrimaryDark,

    secondary = CourseColors.Green,
    onSecondary = CourseColors.White,
    secondaryContainer = CourseColors.Success.copy(alpha = 0.2f),
    onSecondaryContainer = CourseColors.Success,

    background = CourseColors.Background,
    onBackground = CourseColors.TextPrimary,

    surface = CourseColors.Surface,
    onSurface = CourseColors.TextPrimary,

    error = CourseColors.Error,
    onError = CourseColors.White,

    surfaceVariant = CourseColors.Surface
)

@Composable
fun CourseAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = CourseTypography,
        content = content
    )
}

@Composable
fun textFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = CourseColors.TextFieldBackground,
    unfocusedContainerColor = CourseColors.TextFieldBackground,
    disabledContainerColor = CourseColors.TextFieldBackground,
    focusedTextColor = CourseColors.TextPrimary,
    unfocusedTextColor = CourseColors.TextPrimary,
    disabledTextColor = CourseColors.TextPrimary,
    focusedPlaceholderColor = CourseColors.TextHint,
    unfocusedPlaceholderColor = CourseColors.TextHint,
    disabledPlaceholderColor = CourseColors.TextHint,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent
)
