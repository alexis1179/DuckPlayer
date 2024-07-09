package com.alexis1179.duckplayer.view.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexis1179.duckplayer.view.HomeScreen

@Composable
fun NavAppHost(modifier: Modifier = Modifier,
               navController: NavHostController,
               startDestination: String = NavigationItem.Home.route) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = NavigationItem.Home.route
        ) {
            HomeScreen()
        }
    }
}