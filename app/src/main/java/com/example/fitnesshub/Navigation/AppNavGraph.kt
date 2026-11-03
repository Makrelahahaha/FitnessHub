package com.example.fitnesshub.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnesshub.ui.training.TrainingScreen
import com.example.fitnesshub.ui.home.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateToTraining = {
                    navController.navigate(Screen.Training.route)
                }
            )
        }

        composable(route = Screen.Training.route) {
            TrainingScreen (
                onNavigateToHome = {
                    navController.popBackStack()
                }
            )
        }
    }
}