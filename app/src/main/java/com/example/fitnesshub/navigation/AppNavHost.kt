package com.example.fitnesshub.navigation

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
import com.example.fitnesshub.ui.calories.caloriesGraph
import com.example.fitnesshub.ui.home.homeGraph
import com.example.fitnesshub.ui.plans.plansGraph
import com.example.fitnesshub.ui.progress.progressGraph
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
            modifier = Modifier.padding(innerPadding)
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
    val alreadyOnDestination = currentDestination
        ?.hierarchy
        ?.any { it.route == destination.route } == true

    if (alreadyOnDestination) {
        // Tapping the active tab returns to the root of that section.
        popBackStack(destination.route, inclusive = false)
        return
    }

    navigate(destination.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
