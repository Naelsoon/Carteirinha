package com.example.store.app.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.store.core.designsystem.theme.StoreTheme

@Composable
fun App() {
    StoreTheme() {
        val navController = rememberNavController()
        AppNavHost(
            navController = navController
        )
    }
}