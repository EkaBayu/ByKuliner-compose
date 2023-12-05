package com.example.bykuliner.ui.navigation

sealed class Screen(val route: String){
    object Home : Screen("home")
    object About : Screen("about")
    object DetailKuliner: Screen("home/{kulinerId}"){
        fun createRoute(kulinerId: Int) = "home/$kulinerId"
    }
}