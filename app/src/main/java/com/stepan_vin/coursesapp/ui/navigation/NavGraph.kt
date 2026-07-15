package com.stepan_vin.coursesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.stepan_vin.coursesapp.feature.auth.presentation.LoginScreen
import com.stepan_vin.coursesapp.feature.coursedetails.presentation.CourseDetailsScreen
import com.stepan_vin.coursesapp.feature.favorites.presentation.FavoritesScreen
import com.stepan_vin.coursesapp.feature.main.presentation.MainScreen
import com.stepan_vin.coursesapp.feature.profile.presentation.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.AuthGraph.route
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation(
            startDestination = Screen.Login.route,
            route = Screen.AuthGraph.route
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Screen.HomeGraph.route) {
                            popUpTo(Screen.AuthGraph.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        navigation(
            startDestination = Screen.Main.route,
            route = Screen.HomeGraph.route
        ) {
            composable(Screen.Main.route) { backStackEntry ->
                val homeGraphEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Screen.HomeGraph.route)
                }

                MainScreen(
                    homeGraphEntry = homeGraphEntry,
                    onCourseClick = { courseId ->
                        navController.navigate(Screen.CourseDetails.passCourseId(courseId))
                    }
                )
            }

            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    onCourseClick = { courseId ->
                        navController.navigate(Screen.CourseDetails.passCourseId(courseId))
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(
                route = Screen.CourseDetails.route,
                arguments = listOf(
                    navArgument("courseId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
                CourseDetailsScreen(
                    courseId = courseId,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
