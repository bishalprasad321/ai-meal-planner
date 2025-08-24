package com.bishal.mealplanner.utils

sealed class Screen(val route: String) {
    object Splash : Screen(route = "splash_screen")
    object OnBoarding : Screen(route = "onboarding_screen")
    object Home : Screen(route = "home_screen")
    object Result : Screen(route = "result_screen")
    object History : Screen(route = "history_screen")
    object Settings : Screen(route = "settings_screen")
}