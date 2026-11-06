package com.example.store.app.navegation

sealed class Routes(val route: String) {
    data object Login : Routes(route = "Login")
    data object Cadastro : Routes(route = "Cadastro")
    data object Home : Routes(route = "Home")
    data object Carteirinha : Routes(route = "carteirinha_screen")
    data object UnidadeCurricular : Routes(route = "Unidade_Curricular_Screen")
    data object ProfessorTurmas : Routes(route = "professor_turmas_screen")
    data object ProfessorUcs : Routes(route = "professor_ucs_screen")
}
