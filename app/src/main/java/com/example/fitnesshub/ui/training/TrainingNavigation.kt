package com.example.fitnesshub.ui.training

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.fitnesshub.navigation.TopLevelDestination

object TrainingRoutes {
    const val Main = "training/main"
    const val Calendar = "training/calendar"
    const val Library = "training/library"

    const val WorkoutIdArg = "workoutId"
    const val Details = "training/details/{$WorkoutIdArg}"

    fun details(workoutId: String) = "training/details/$workoutId"
}

fun NavGraphBuilder.trainingGraph(navController: NavHostController) {
    navigation(
        route = TopLevelDestination.Training.route,
        startDestination = TrainingRoutes.Main
    ) {
        composable(TrainingRoutes.Main) {
            TrainingScreen(
                onNavigateToCalendar = {
                    navController.navigate(TrainingRoutes.Calendar)
                },
                onNavigateToLibrary = {
                    navController.navigate(TrainingRoutes.Library)
                },
                onNavigateToWorkoutDetails = {
                    navController.navigate(TrainingRoutes.details("sample-workout"))
                }
            )
        }

        composable(TrainingRoutes.Calendar) {
            TrainingCalendarScreen()
        }

        composable(TrainingRoutes.Library) {
            ExerciseLibraryScreen()
        }

        composable(
            route = TrainingRoutes.Details,
            arguments = listOf(
                navArgument(TrainingRoutes.WorkoutIdArg) {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            WorkoutDetailsScreen(
                workoutId = entry.arguments
                    ?.getString(TrainingRoutes.WorkoutIdArg)
                    .orEmpty()
            )
        }
    }
}
