package com.stepan_vin.coursesapp.core.common.utils

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    return this.matches(emailRegex)
}
