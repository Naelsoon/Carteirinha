package com.example.store.app.navegation
sealed class Routes (val route: String){
    data object Login : Routes(route = "Login")
    data object Home : Routes(route = "Home")
}