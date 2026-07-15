package com.stepan_vin.coursesapp.ui.navigation

sealed class Screen(val route: String) {
    data object AuthGraph : Screen("auth_graph")
    data object HomeGraph : Screen("home_graph")

    data object Login : Screen("login")
    data object Main : Screen("main")
    data object Favorites : Screen("favorites")
    data object Profile : Screen("profile")
    object CourseDetails : Screen("course_details/{courseId}") {
        fun passCourseId(courseId: Int): String {
            return "course_details/$courseId"
        }
    }
}
