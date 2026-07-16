package com.stepan_vin.coursesapp.core.designsystem.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object CourseColors {
    val Background = Color(0xFF151515)
    val Surface = Color(0xFF24252A)
    val TextFieldBackground = Color(0xFF32333A)
    val TextPrimary = Color(0xFFF2F2F3)
    val TextSecondary = Color(0xFFF2F2F3)
    val TextHint = Color(0xFF8A8B90)
    val Divider = Color(0xFF4D555E)
    val Green = Color(0xFF12B956)
    val BlueGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF2683ED),
            Color(0xFF2683ED)
        )
    )
    val OrangeGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF98509),
            Color(0xFFF95D00)
        )
    )
    val Primary = Green
    val PrimaryDark = Color(0xFF0E8F42)
    val PrimaryLight = Color(0xFF5CE08A)
    val Error = Color(0xFFE53935)
    val Success = Green
    val White = Color(0xFFFFFFFF)
}
