package com.example.fitnesshub.Navigation

sealed class Screen(val route: String){
    object Home : Screen("home")
    object Training : Screen("training")
}