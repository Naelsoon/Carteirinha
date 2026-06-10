package com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.data.dataSource
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.presentation.component.UnidadeCurricularCard

@Composable
fun UnidadeCurricularScreen(
    modifier: Modifier = Modifier
) {
    val unidadesCurriculares = dataSource()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Um pequeno respiro entre a barra de status e o título
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Unidades Curriculares",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(unidadesCurriculares) { unidadeCurricular ->
                UnidadeCurricularCard(unidadeCurricular = unidadeCurricular)
            }


            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

