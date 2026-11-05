package com.example.fitnesshub.ui.progress

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.fitnesshub.navigation.TopLevelDestination

object ProgressRoutes {
    const val Main = "progress/main"
    const val Challenges = "progress/challenges"
}

fun NavGraphBuilder.progressGraph(navController: NavHostController) {
    navigation(
        route = TopLevelDestination.Progress.route,
        startDestination = ProgressRoutes.Main
    ) {
        composable(ProgressRoutes.Main) {
            ProgressScreen(
                onNavigateToChallenges = {
                    navController.navigate(ProgressRoutes.Challenges)
                }
            )
        }

        composable(ProgressRoutes.Challenges) {
            ChallengesScreen()
        }
    }
}
