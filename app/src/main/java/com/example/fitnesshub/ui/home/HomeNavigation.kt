package com.example.fitnesshub.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.fitnesshub.navigation.TopLevelDestination
import com.example.fitnesshub.ui.profile.PersonalDataScreen
import com.example.fitnesshub.ui.profile.ProfileRoutes
import com.example.fitnesshub.ui.profile.SettingsScreen

object HomeRoutes {
    const val Main = "home/main"
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(
        route = TopLevelDestination.Home.route,
        startDestination = HomeRoutes.Main
    ) {
        composable(HomeRoutes.Main) {
            HomeScreen(
                onNavigateToPersonalData = {
                    navController.navigate(ProfileRoutes.PersonalData)
                },
                onNavigateToSettings = {
                    navController.navigate(ProfileRoutes.Settings)
                }
            )
        }

        composable(ProfileRoutes.PersonalData) {
            PersonalDataScreen()
        }

        composable(ProfileRoutes.Settings) {
            SettingsScreen()
        }
    }
}
