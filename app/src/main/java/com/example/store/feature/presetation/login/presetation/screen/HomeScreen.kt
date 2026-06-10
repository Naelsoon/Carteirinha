package com.example.store.feature.store.presetation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.store.app.navegation.Routes

// Mantendo o padrão de cores da identidade visual do seu app
val buttonBackgroundColor = Color(0xFF1F222A)   // Fundo escuro igual ao do Form
val buttonBorderColor = Color(0xFF8A2BE2)       // Roxo dos inputs/botões
val buttonTextColorSecundario = Color(0xFF9E9E9E) // Cinza dos labels

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Menu Principal",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        BotaoCardClicavel(
            titulo = "Carteirinha",
            subtitulo = "Clique para ver sua carteirinha",
            onButtonClick = {

                navController.navigate(Routes.Carteirinha.route)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotaoCardClicavel(
            titulo = "Unidade Curricular",
            subtitulo = "Clique para ver sua Unidade Curricular",
            onButtonClick = {
                navController.navigate(Routes.UnidadeCurricular.route)
            }
        )
    }
}

@Composable
fun BotaoCardClicavel(
    titulo: String,
    subtitulo: String,
    onButtonClick: () -> Unit
) {
    OutlinedButton(
        onClick = onButtonClick,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, buttonBorderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = buttonBackgroundColor
        ),

        contentPadding = PaddingValues(16.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = titulo,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitulo,
                color = buttonTextColorSecundario,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )
        }
    }
}