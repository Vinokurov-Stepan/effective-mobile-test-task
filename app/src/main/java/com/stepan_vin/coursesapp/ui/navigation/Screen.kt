package com.stepan_vin.coursesapp.ui.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login")
    object Main : Screen("main")
    object Favorites : Screen("favorites")
    object Profile : Screen("profile")
    object CourseDetails : Screen("course_details/{courseId}") {
        fun passCourseId(courseId: Int): String {
            return "course_details/$courseId"
        }
    }
}
