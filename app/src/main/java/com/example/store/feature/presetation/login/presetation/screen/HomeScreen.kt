package com.example.store.feature.store.presetation.screen

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.store.app.navegation.Routes
import com.example.store.core.network.SessionManager

// Mantendo o padrão de cores da identidade visual do seu app
val buttonBackgroundColor = Color(0xFF1F222A)   // Fundo escuro igual ao do Form
val buttonBorderColor = Color(0xFF8A2BE2)       // Roxo dos inputs/botões
val buttonTextColorSecundario = Color(0xFF9E9E9E) // Cinza dos labels

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val session by SessionManager.currentUser.collectAsStateWithLifecycle()
    val isProfessor = session.isProfessor

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF121318))
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Barra Superior do Usuário Conectado
        Row(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .background(buttonBackgroundColor, RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Olá, ${session.nome.ifBlank { "Usuário" }}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                if (isProfessor) Color(0xFF38234E) else Color(0xFF1E3A2B),
                                RoundedCornerShape(6.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = if (isProfessor) "PROFESSOR" else "ALUNO",
                            color = if (isProfessor) buttonBorderColor else Color(0xFF00FF7F),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (session.matricula.isNotBlank()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isProfessor) "Docente: ${session.matricula}" else "RA: ${session.matricula}",
                            color = buttonTextColorSecundario,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            IconButton(
                onClick = {
                    SessionManager.logout()
                    navController.navigate(Routes.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Sair",
                    tint = Color(0xFFFF5252)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Exibição condicional estrita de acordo com o perfil autenticado:
        if (isProfessor) {
            // ================= SEÇÃO EXCLUSIVA DO PROFESSOR =================
            SectionHeader(titulo = "PAINEL DO PROFESSOR", icon = Icons.Default.Person)

            Spacer(modifier = Modifier.height(12.dp))

            BotaoCardClicavelComIcone(
                titulo = "Minhas Turmas",
                subtitulo = "Consultar turmas vinculadas via API",
                icon = Icons.Default.Groups,
                badge = "API Retrofit",
                onButtonClick = {
                    navController.navigate(Routes.ProfessorTurmas.route)
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            BotaoCardClicavelComIcone(
                titulo = "UCs & Lançamento de Notas",
                subtitulo = "Controle de aulas e lançamento de notas ao vivo",
                icon = Icons.Default.EditNote,
                badge = "Notas Ao Vivo",
                onButtonClick = {
                    navController.navigate(Routes.ProfessorUcs.route)
                }
            )
        } else {
            // ================= SEÇÃO EXCLUSIVA DO ALUNO =================
            SectionHeader(titulo = "PAINEL DO ALUNO", icon = Icons.Default.School)

            Spacer(modifier = Modifier.height(12.dp))

            BotaoCardClicavelComIcone(
                titulo = "Carteirinha Digital",
                subtitulo = "Visualizar QR Code, RA e identificação estudantil",
                icon = Icons.Default.Badge,
                badge = "Principal",
                onButtonClick = {
                    navController.navigate(Routes.Carteirinha.route)
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            BotaoCardClicavelComIcone(
                titulo = "Unidades Curriculares (Aluno)",
                subtitulo = "Boletim sincronizado com as notas do professor",
                icon = Icons.Default.Book,
                badge = "Sincronizado",
                onButtonClick = {
                    navController.navigate(Routes.UnidadeCurricular.route)
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SectionHeader(titulo: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = buttonBorderColor,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = titulo,
            color = buttonBorderColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun BotaoCardClicavelComIcone(
    titulo: String,
    subtitulo: String,
    icon: ImageVector,
    badge: String? = null,
    onButtonClick: () -> Unit
) {
    OutlinedButton(
        onClick = onButtonClick,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.2.dp, buttonBorderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = buttonBackgroundColor
        ),
        contentPadding = PaddingValues(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF2B2245), RoundedCornerShape(10.dp))
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = buttonBorderColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = titulo,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )

                    if (badge != null) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF283226), RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = badge,
                                color = Color(0xFF81C784),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitulo,
                    color = buttonTextColorSecundario,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 13.sp
                )
            }
        }
    }
}
