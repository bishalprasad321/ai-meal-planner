package com.bishal.mealplanner.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bishal.mealplanner.ui.home.HomeScreen
import com.bishal.mealplanner.ui.onboarding.OnBoardingScreen
import com.bishal.mealplanner.ui.splash.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(navController = navController)
        }
        composable(
            route = Screen.OnBoarding.route
        ) {
            OnBoardingScreen(navController = navController)
        }
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }
    }
}