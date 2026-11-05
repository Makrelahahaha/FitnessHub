package com.example.fitnesshub.ui.plans

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.fitnesshub.navigation.TopLevelDestination

object PlansRoutes {
    const val Main = "plans/main"

    const val PlanIdArg = "planId"
    const val Details = "plans/details/{$PlanIdArg}"

    fun details(planId: String) = "plans/details/$planId"
}

fun NavGraphBuilder.plansGraph(navController: NavHostController) {
    navigation(
        route = TopLevelDestination.Plans.route,
        startDestination = PlansRoutes.Main
    ) {
        composable(PlansRoutes.Main) {
            PlansScreen(
                onNavigateToPlanDetails = {
                    navController.navigate(PlansRoutes.details("sample-plan"))
                }
            )
        }

        composable(
            route = PlansRoutes.Details,
            arguments = listOf(
                navArgument(PlansRoutes.PlanIdArg) {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            PlanDetailsScreen(
                planId = entry.arguments
                    ?.getString(PlansRoutes.PlanIdArg)
                    .orEmpty()
            )
        }
    }
}
