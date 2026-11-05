package com.example.fitnesshub.ui.calories

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.fitnesshub.navigation.TopLevelDestination

object CaloriesRoutes {
    const val Main = "calories/main"
    const val WaterTracker = "calories/water-tracker"
}

fun NavGraphBuilder.caloriesGraph(navController: NavHostController) {
    navigation(
        route = TopLevelDestination.Calories.route,
        startDestination = CaloriesRoutes.Main
    ) {
        composable(CaloriesRoutes.Main) {
            CaloriesScreen(
                onNavigateToWaterTracker = {
                    navController.navigate(CaloriesRoutes.WaterTracker)
                }
            )
        }

        composable(CaloriesRoutes.WaterTracker) {
            WaterTrackerScreen()
        }
    }
}
