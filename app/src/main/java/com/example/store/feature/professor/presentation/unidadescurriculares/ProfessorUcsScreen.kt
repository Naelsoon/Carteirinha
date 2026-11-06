package com.example.store.feature.professor.presentation.unidadescurriculares

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.store.feature.professor.domain.model.ProfessorUc
import com.example.store.feature.professor.presentation.components.EmptyStateView
import com.example.store.feature.professor.presentation.components.ErrorStateView
import com.example.store.feature.professor.presentation.components.LancarNotasDialog
import com.example.store.feature.professor.presentation.components.LoadingStateView
import com.example.store.feature.professor.presentation.unidadescurriculares.components.ProfessorUcCard

private val darkBackground = Color(0xFF121318)
private val purpleAccent = Color(0xFF8A2BE2)

@Composable
fun ProfessorUcsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfessorUcsViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var selectedUcParaNotas by remember { mutableStateOf<ProfessorUc?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(darkBackground)
            .statusBarsPadding()
    ) {
        // Barra Superior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Unidades Curriculares",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "Disciplinas Ministradas",
                    color = purpleAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            IconButton(onClick = { viewModel.carregarUnidadesCurriculares() }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Recarregar",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Conteúdo da Tela conforme UiState
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            when (val state = uiState) {
                is ProfessorUcsUiState.Loading -> {
                    LoadingStateView(mensagem = "Consultando UCs ministradas na API...")
                }
                is ProfessorUcsUiState.Empty -> {
                    EmptyStateView(
                        mensagem = "Nenhuma unidade curricular encontrada",
                        onRecarregar = { viewModel.carregarUnidadesCurriculares() }
                    )
                }
                is ProfessorUcsUiState.Error -> {
                    ErrorStateView(
                        mensagemErro = state.message,
                        onTentarNovamente = { viewModel.carregarUnidadesCurriculares() }
                    )
                }
                is ProfessorUcsUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(state.ucs, key = { it.id }) { uc ->
                            ProfessorUcCard(
                                uc = uc,
                                onLancarNotasClick = {
                                    selectedUcParaNotas = uc
                                }
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }
        }
    }

    // Diálogo de Lançamento de Notas conectado ao Aluno
    selectedUcParaNotas?.let { uc ->
        LancarNotasDialog(
            disciplinaNome = uc.nome,
            ucId = uc.id.toString(),
            onDismiss = { selectedUcParaNotas = null },
            onSalvo = {
                selectedUcParaNotas = null
                Toast.makeText(
                    context,
                    "Notas da disciplina '${uc.nome}' salvas! O aluno já pode visualizá-las no portal.",
                    Toast.LENGTH_LONG
                ).show()
            }
        )
    }
}
