package com.example.store.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.store.app.navegation.App
import com.example.store.core.designsystem.theme.StoreTheme
import com.example.store.core.network.SessionManager
import com.example.store.core.network.TokenManager
import com.example.store.feature.professor.data.repository.NotasRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialização dos serviços de dados e sessão
        TokenManager.init(applicationContext)
        SessionManager.init(applicationContext)
        NotasRepository.init(applicationContext)

        enableEdgeToEdge()
        setContent {
            StoreTheme {
                App()
            }
        }
    }
}
