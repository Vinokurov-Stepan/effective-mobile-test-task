package com.stepan_vin.coursesapp.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = CourseColors.Green,
    onPrimary = CourseColors.TextSecondary,
    primaryContainer = CourseColors.PrimaryLight,
    onPrimaryContainer = CourseColors.PrimaryDark,

    secondary = CourseColors.Green,
    onSecondary = CourseColors.TextSecondary,
    secondaryContainer = CourseColors.Green.copy(alpha = 0.2f),
    onSecondaryContainer = CourseColors.Green,

    background = CourseColors.Background,
    onBackground = CourseColors.TextPrimary,

    surface = CourseColors.Surface,
    onSurface = CourseColors.TextPrimary,

    surfaceVariant = CourseColors.Surface
)

data class CustomColors(
    val textFieldBackground: Color,
    val bookmarkBackground: Color,
    val textSecondary: Color,
    val textHint: Color,
    val divider: Color,
    val blueGradientStart: Color,
    val blueGradientEnd: Color,
    val orangeGradientStart: Color,
    val orangeGradientEnd: Color
)

val CustomColorScheme = CustomColors(
    textFieldBackground = CourseColors.TextFieldBackground,
    bookmarkBackground = CourseColors.BookmarkBackground,
    textSecondary = CourseColors.TextSecondary,
    textHint = CourseColors.TextHint,
    divider = CourseColors.Divider,
    blueGradientStart = CourseColors.BlueGradientStart,
    blueGradientEnd = CourseColors.BlueGradientEnd,
    orangeGradientStart = CourseColors.OrangeGradientStart,
    orangeGradientEnd = CourseColors.OrangeGradientEnd
)

val blueGradient = Brush.verticalGradient(
    colors = listOf(
        CustomColorScheme.blueGradientStart,
        CustomColorScheme.blueGradientEnd
    )
)

val orangeGradient = Brush.verticalGradient(
    colors = listOf(
        CustomColorScheme.orangeGradientStart,
        CustomColorScheme.orangeGradientEnd
    )
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
    focusedContainerColor = CustomColorScheme.textFieldBackground,
    unfocusedContainerColor = CustomColorScheme.textFieldBackground,
    disabledContainerColor = CustomColorScheme.textFieldBackground,
    focusedTextColor = CourseColors.TextPrimary,
    unfocusedTextColor = CourseColors.TextPrimary,
    disabledTextColor = CourseColors.TextPrimary,
    focusedPlaceholderColor = CustomColorScheme.textHint,
    unfocusedPlaceholderColor = CustomColorScheme.textHint,
    disabledPlaceholderColor = CustomColorScheme.textHint,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent
)

@Composable
fun searchFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = CourseColors.Surface,
    unfocusedContainerColor = CourseColors.Surface,
    disabledContainerColor = CourseColors.Surface,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent
)

val MaterialTheme.customColors: CustomColors
    @Composable
    get() = CustomColorScheme
