package com.o2o.o2oweather

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.o2o.o2oweather.navigation.AppNavHost
import com.o2o.o2oweather.navigation.NavigationItem

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    AppNavHost(
        navController = navController,
        startDestination = NavigationItem.Splash.route
    )
}