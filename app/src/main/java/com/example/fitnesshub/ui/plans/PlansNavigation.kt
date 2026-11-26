package com.example.fitnesshub.ui.plans

import android.net.Uri
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

    fun details(planId: String): String {
        val safeId = planId.ifBlank { "unknown" }
        return "plans/details/${Uri.encode(safeId)}"
    }
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
            val rawId = entry.arguments?.getString(PlansRoutes.PlanIdArg).orEmpty()
            PlanDetailsScreen(
                planId = Uri.decode(rawId),
                onNavigateBack = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    } else {
                        navController.navigate(PlansRoutes.Main) {
                            popUpTo(PlansRoutes.Main) { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}