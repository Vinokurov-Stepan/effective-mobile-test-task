package com.stepan_vin.coursesapp.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stepan_vin.coursesapp.R

@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    val items = listOf(
        BottomNavItem(
            route = Screen.Main.route,
            title = stringResource(R.string.main_tab),
            icon = R.drawable.mainscreen
        ),
        BottomNavItem(
            route = Screen.Favorites.route,
            title = stringResource(R.string.favourite_tab),
            icon = R.drawable.favouritescreen
        ),
        BottomNavItem(
            route = Screen.Profile.route,
            title = stringResource(R.string.profile_tab),
            icon = R.drawable.profilescreen
        )
    )

    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}

private data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: Int
)
