package com.example.fitnesshub.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fitnesshub.ui.calories.CaloriesRoutes
import com.example.fitnesshub.ui.calories.caloriesGraph
import com.example.fitnesshub.ui.home.HomeRoutes
import com.example.fitnesshub.ui.home.homeGraph
import com.example.fitnesshub.ui.plans.PlansRoutes
import com.example.fitnesshub.ui.plans.plansGraph
import com.example.fitnesshub.ui.progress.ProgressRoutes
import com.example.fitnesshub.ui.progress.progressGraph
import com.example.fitnesshub.ui.training.TrainingRoutes
import com.example.fitnesshub.ui.training.trainingGraph

@Composable
fun AppNavHost(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    Scaffold(
        bottomBar = {
            AppBottomBar(
                currentDestination = currentDestination,
                onItemSelected = navController::navigateToTopLevel
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TopLevelDestination.Home.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { fadeIn(animationSpec = tween(200)) },
            exitTransition = { fadeOut(animationSpec = tween(200)) },
            popEnterTransition = { fadeIn(animationSpec = tween(200)) },
            popExitTransition = { fadeOut(animationSpec = tween(200)) }
        ) {
            homeGraph(navController)
            trainingGraph(navController)
            caloriesGraph(navController)
            plansGraph(navController)
            progressGraph(navController)
        }
    }
}

private fun NavHostController.navigateToTopLevel(destination: TopLevelDestination) {
    val startRoute = startRouteFor(destination)
    val alreadyOnDestination = currentDestination
        ?.hierarchy
        ?.any { it.route == destination.route } == true

    if (alreadyOnDestination) {
        if (startRoute != null) {
            popBackStack(startRoute, inclusive = false)
        }
        return
    }

    navigate(destination.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = false
        }
        launchSingleTop = true
        restoreState = false
    }

    if (startRoute != null) {
        popBackStack(startRoute, inclusive = false)
    }
}

private fun startRouteFor(destination: TopLevelDestination): String? = when (destination) {
    TopLevelDestination.Home -> HomeRoutes.Main
    TopLevelDestination.Training -> TrainingRoutes.Main
    TopLevelDestination.Calories -> CaloriesRoutes.Main
    TopLevelDestination.Plans -> PlansRoutes.Main
    TopLevelDestination.Progress -> ProgressRoutes.Main
}