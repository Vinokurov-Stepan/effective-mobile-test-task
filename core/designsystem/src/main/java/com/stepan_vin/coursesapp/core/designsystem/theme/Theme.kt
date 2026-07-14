package com.stepan_vin.coursesapp.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
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

private val DarkColorScheme = darkColorScheme(
    primary = CourseColors.PrimaryLight,
    onPrimary = CourseColors.Black,
    primaryContainer = CourseColors.PrimaryDark,
    onPrimaryContainer = CourseColors.White,

    secondary = CourseColors.Green,
    onSecondary = CourseColors.Black,
    secondaryContainer = CourseColors.Success.copy(alpha = 0.3f),
    onSecondaryContainer = CourseColors.Green,

    background = Color(0xFF121212),
    onBackground = Color(0xFFE0E0E0),

    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),

    error = CourseColors.Error,
    onError = CourseColors.White,

    surfaceVariant = Color(0xFF2C2C2C)
)

@Composable
fun CourseAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CourseTypography,
        content = content
    )
}
