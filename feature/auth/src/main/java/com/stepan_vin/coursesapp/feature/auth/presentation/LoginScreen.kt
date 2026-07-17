package com.stepan_vin.coursesapp.feature.auth.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseAppTheme
import com.stepan_vin.coursesapp.core.designsystem.theme.CourseTypography
import com.stepan_vin.coursesapp.core.designsystem.theme.blueGradient
import com.stepan_vin.coursesapp.core.designsystem.theme.customColors
import com.stepan_vin.coursesapp.core.designsystem.theme.orangeGradient
import com.stepan_vin.coursesapp.feature.auth.R
import com.stepan_vin.coursesapp.feature.auth.components.AuthTextField
import com.stepan_vin.coursesapp.feature.auth.components.SocialAuthButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val email by viewModel.email
    val password by viewModel.password

    val vkUrl = stringResource(R.string.auth_vk_url)
    val okUrl = stringResource(R.string.auth_ok_url)

    LoginScreenContent(
        email = email,
        password = password,
        isLoginEnabled = viewModel.isLoginEnabled,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = onLoginSuccess,
        onVkClick = { openBrowser(context, vkUrl) },
        onOkClick = { openBrowser(context, okUrl) }
    )
}

@Composable
fun LoginScreenContent(
    email: String,
    password: String,
    isLoginEnabled: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onVkClick: () -> Unit,
    onOkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = stringResource(R.string.auth_login_title),
            style = CourseTypography.headlineLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(28.dp))

        AuthTextField(
            value = email,
            onValueChange = onEmailChange,
            labelResId = R.string.auth_email_label,
            placeholderResId = R.string.auth_email_placeholder,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            value = password,
            onValueChange = onPasswordChange,
            labelResId = R.string.auth_password_label,
            placeholderResId = R.string.auth_password_placeholder,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onLoginClick,
            enabled = isLoginEnabled,
            modifier = modifier
                .fillMaxWidth()
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = stringResource(R.string.auth_login_button),
                style = CourseTypography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.auth_register_prefix),
                style = CourseTypography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Text(
                text = stringResource(R.string.auth_register_button),
                style = CourseTypography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .clickable {}
                    .padding(start = 5.dp)
            )
        }

        Text(
            text = stringResource(R.string.auth_forgot_password_button),
            style = CourseTypography.bodySmall.copy(
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .clickable {}
                .padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.customColors.divider
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SocialAuthButton(
                onClick = onVkClick,
                gradient = blueGradient,
                iconResId = R.drawable.vk_icon,
                modifier = Modifier.weight(1f)
            )

            SocialAuthButton(
                onClick = onOkClick,
                gradient = orangeGradient,
                iconResId = R.drawable.ok_icon,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

private fun openBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    context.startActivity(intent)
}

@Preview(
    name = "Login Screen",
    showSystemUi = false
)
@Composable
private fun LoginScreenPreview() {
    CourseAppTheme {
        LoginScreenContent(
            email = "user@example.com",
            password = "secure_password",
            isLoginEnabled = true,
            onEmailChange = {},
            onPasswordChange = {},
            onLoginClick = {},
            onVkClick = {},
            onOkClick = {}
        )
    }
}
