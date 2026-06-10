package com.example.store.app.navegation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.store.feature.store.presetation.screen.HomeScreen
import com.example.store.feature.store.presetation.screen.LoginScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {

            LoginScreen(
                navController = navController
            )
        }

        composable(Routes.Home.route) {

            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->

                HomeScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}