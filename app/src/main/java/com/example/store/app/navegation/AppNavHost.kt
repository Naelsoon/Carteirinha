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
import com.example.store.feature.professor.presentation.turmas.TurmasScreen
import com.example.store.feature.professor.presentation.unidadescurriculares.ProfessorUcsScreen
import com.example.store.feature.store.presetation.screen.CadastroScreen
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

        composable(Routes.Cadastro.route) {
            CadastroScreen(
                navController = navController
            )
        }

        composable(Routes.Home.route) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }

        composable(Routes.Carteirinha.route) {
            CarteirinhaScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Routes.UnidadeCurricular.route) {
            UnidadeCurricularScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        // ================= ROTAS DO PROFESSOR =================
        composable(Routes.ProfessorTurmas.route) {
            TurmasScreen(
                onBackClick = {
                    if (!navController.popBackStack()) {
                        navController.navigate(Routes.Home.route)
                    }
                },
                onNavigateToUcs = { navController.navigate(Routes.ProfessorUcs.route) }
            )
        }

        composable(Routes.ProfessorUcs.route) {
            ProfessorUcsScreen(
                onBackClick = {
                    if (!navController.popBackStack()) {
                        navController.navigate(Routes.Home.route)
                    }
                }
            )
        }
    }
}
