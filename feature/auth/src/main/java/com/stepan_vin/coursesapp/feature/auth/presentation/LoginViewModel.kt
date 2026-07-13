package com.stepan_vin.coursesapp.feature.auth.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.stepan_vin.coursesapp.core.common.utils.isValidEmail

class LoginViewModel : ViewModel() {

    private val _email = mutableStateOf("")
    val email = _email

    private val _password = mutableStateOf("")
    val password = _password

    val isLoginEnabled: Boolean
        get() = email.value.isValidEmail() && password.value.isNotEmpty()

    fun showError(email: String): Boolean {
        return email.isNotEmpty() && !email.isValidEmail()
    }

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }
}
