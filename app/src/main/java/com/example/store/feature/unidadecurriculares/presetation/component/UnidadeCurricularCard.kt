package com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.domain.model.UnidadeCurricular


val ucBgColor = Color(0xFF1F222A)
val ucBorderColor = Color(0xFF8A2BE2)
val ucTextSecondary = Color(0xFF9E9E9E)

@Composable
fun UnidadeCurricularCard(
    modifier: Modifier = Modifier,
    unidadeCurricular: UnidadeCurricular
) {
    Card(
        modifier = modifier.fillMaxWidth(0.95f),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, ucBorderColor),
        colors = CardDefaults.cardColors(
            containerColor = ucBgColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = unidadeCurricular.nome,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp
            )

            Text(
                text = "Professor: ${unidadeCurricular.professor}",
                color = ucTextSecondary,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "N1: ${unidadeCurricular.nota1}", color = Color.White, fontSize = 14.sp)
                Text(text = "N2: ${unidadeCurricular.nota2}", color = Color.White, fontSize = 14.sp)
                Text(
                    text = "Média: ${unidadeCurricular.media}",
                    color = ucBorderColor, // Destaca a média com a cor roxa
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 14.sp
                )
            }


            Text(
                text = "Faltas: ${unidadeCurricular.faltas}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFFF4545), // Vermelho discreto para destacar as faltas
                fontSize = 13.sp
            )
        }
    }
}