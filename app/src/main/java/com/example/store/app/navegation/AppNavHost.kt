package com.example.store.app.navegation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.store.feature.carteirinha.presetation.screen.CarteirinhaScreen
import com.example.store.feature.store.presetation.screen.HomeScreen
import com.example.store.feature.store.presetation.screen.LoginScreen
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen

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
                // CORREÇÃO AQUI: Passando o modifier correto e o navController que faltava
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }

        }

        composable(Routes.Carteirinha.route) {
            CarteirinhaScreen()
        }


        composable(Routes.UnidadeCurricular.route) {
            UnidadeCurricularScreen()
        }
    }
}