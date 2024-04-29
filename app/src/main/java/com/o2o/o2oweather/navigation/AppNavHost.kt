package com.o2o.o2oweather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.o2o.ui.features.splash.SplashFragment
import com.o2o.ui.features.weather.WeatherFragment

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashFragment{
                navController.navigate(NavigationItem.Home.route)
            }
        }
        composable(NavigationItem.Home.route) {
            WeatherFragment()
        }
    }
}