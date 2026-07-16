package com.stepan_vin.coursesapp.feature.auth.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseColors
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseTypography
import com.stepan_vin.coursesapp.core.designsystem.theme.textFieldColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelResId: Int,
    placeholderResId: Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier) {
        Text(
            text = stringResource(labelResId),
            style = CourseTypography.titleMedium.copy(
                color = CourseColors.TextPrimary
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            textStyle = CourseTypography.bodyMedium.copy(
                color = CourseColors.TextPrimary
            ),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            singleLine = true,
            cursorBrush = SolidColor(CourseColors.TextPrimary),
            decorationBox = { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    isError = isError,
                    placeholder = {
                        Text(
                            text = stringResource(placeholderResId),
                            style = CourseTypography.bodyMedium.copy(
                                color = CourseColors.TextHint
                            )
                        )
                    },
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                    container = {
                        OutlinedTextFieldDefaults.Container(
                            enabled = true,
                            isError = isError,
                            interactionSource = interactionSource,
                            colors = textFieldColors(),
                            shape = RoundedCornerShape(20.dp),
                            focusedBorderThickness = 1.dp,
                            unfocusedBorderThickness = 1.dp
                        )
                    }
                )
            }
        )
    }
}
