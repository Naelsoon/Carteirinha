package com.example.store.feature.carteirinha.presetation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafaelcosta.myapplication.QrCode

// Mantendo a paleta de cores padrão do seu app
val carteirinhaBgColor = Color(0xFF1F222A)       // Mesmo fundo dos inputs e cards
val carteirinhaBorderColor = Color(0xFF8A2BE2)   // Roxo assinatura
val carteirinhaTextCinza = Color(0xFF9E9E9E)     // Cinza secundário

@Composable
fun CarteirinhaScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        // Título Superior da Tela
        Text(
            text = "Sua Carteirinha",
            fontSize = 26.sp,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        // O "Card" Físico Virtual da Carteirinha
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, carteirinhaBorderColor),
            colors = CardDefaults.cardColors(
                containerColor = carteirinhaBgColor
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "IDENTIFICAÇÃO ESTUDANTIL",
                    color = carteirinhaBorderColor, // Texto em Roxo
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(24.dp))


                Box(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    QrCode(
                          "00001117506393"
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))


                Text(
                    text = "RA: 900000000017565",
                    color = Color.White,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Status: Ativo",
                    color = Color(0xFF00FF7F),
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

