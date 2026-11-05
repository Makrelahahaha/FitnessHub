package com.example.fitnesshub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.fitnesshub.navigation.AppNavHost
import com.example.fitnesshub.ui.theme.FitnessHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessHubTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
