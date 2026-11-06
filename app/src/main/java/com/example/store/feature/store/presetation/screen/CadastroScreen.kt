package com.example.store.feature.store.presetation.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.store.app.navegation.Routes
import com.example.store.core.network.dto.RegisterRequestDto
import com.example.store.feature.store.presetation.AuthUiState
import com.example.store.feature.store.presetation.AuthViewModel

private val backgroundCol = Color(0xFF121318)
private val cardBg = Color(0xFF1F222A)
private val purpleAccent = Color(0xFF8A2BE2)
private val textMuted = Color(0xFF9E9E9E)

@Composable
fun CadastroScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var perfilSelecionado by remember { mutableStateOf("ALUNO") } // "ALUNO" ou "PROFESSOR"
    var nome by remember { mutableStateOf("") }
    var matricula by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    var curso by remember { mutableStateOf("Desenvolvimento de Sistemas") }
    var turma by remember { mutableStateOf("2DEVEST-A") }

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is AuthUiState.Success -> {
                val perfil = state.response.perfil.orEmpty()
                Toast.makeText(
                    context,
                    "Cadastro realizado com sucesso! Bem-vindo, ${state.response.nome}",
                    Toast.LENGTH_SHORT
                ).show()

                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
                viewModel.resetState()
            }
            is AuthUiState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundCol)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Topo com voltar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White
                )
            }
            Text(
                text = "Criar Conta",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Seletor de Perfil (ALUNO / PROFESSOR)
        Text(
            text = "Selecione o tipo de acesso:",
            color = textMuted,
            fontSize = 13.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardBg, RoundedCornerShape(12.dp))
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val isAluno = perfilSelecionado == "ALUNO"
            OutlinedButton(
                onClick = { perfilSelecionado = "ALUNO" },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                border = if (isAluno) BorderStroke(1.5.dp, purpleAccent) else null,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isAluno) Color(0xFF2E2445) else Color.Transparent
                )
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = null,
                    tint = if (isAluno) purpleAccent else textMuted,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.size(6.dp))
                Text(
                    text = "Aluno",
                    color = if (isAluno) Color.White else textMuted,
                    fontWeight = FontWeight.Bold
                )
            }

            val isProf = perfilSelecionado == "PROFESSOR"
            OutlinedButton(
                onClick = { perfilSelecionado = "PROFESSOR" },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                border = if (isProf) BorderStroke(1.5.dp, purpleAccent) else null,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isProf) Color(0xFF2E2445) else Color.Transparent
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = if (isProf) purpleAccent else textMuted,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.size(6.dp))
                Text(
                    text = "Professor",
                    color = if (isProf) Color.White else textMuted,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Campos do Formulário
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome Completo") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = purpleAccent) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = cardBg,
                unfocusedContainerColor = cardBg,
                focusedIndicatorColor = purpleAccent,
                unfocusedIndicatorColor = textMuted
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = matricula,
            onValueChange = { matricula = it },
            label = { Text(if (perfilSelecionado == "ALUNO") "RA / Matrícula" else "Matrícula do Docente") },
            leadingIcon = { Icon(Icons.Default.Badge, contentDescription = null, tint = purpleAccent) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = cardBg,
                unfocusedContainerColor = cardBg,
                focusedIndicatorColor = purpleAccent,
                unfocusedIndicatorColor = textMuted
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Usuário para Login") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = purpleAccent) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = cardBg,
                unfocusedContainerColor = cardBg,
                focusedIndicatorColor = purpleAccent,
                unfocusedIndicatorColor = textMuted
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = purpleAccent) },
            trailingIcon = {
                IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                    Icon(
                        imageVector = if (senhaVisivel) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Alternar visibilidade",
                        tint = textMuted
                    )
                }
            },
            visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = cardBg,
                unfocusedContainerColor = cardBg,
                focusedIndicatorColor = purpleAccent,
                unfocusedIndicatorColor = textMuted
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = curso,
            onValueChange = { curso = it },
            label = { Text("Curso") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = cardBg,
                unfocusedContainerColor = cardBg,
                focusedIndicatorColor = purpleAccent,
                unfocusedIndicatorColor = textMuted
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = turma,
            onValueChange = { turma = it },
            label = { Text("Turma") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = cardBg,
                unfocusedContainerColor = cardBg,
                focusedIndicatorColor = purpleAccent,
                unfocusedIndicatorColor = textMuted
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão Cadastrar
        Button(
            onClick = {
                viewModel.cadastrar(
                    RegisterRequestDto(
                        nome = nome,
                        matricula = matricula,
                        login = login,
                        senha = senha,
                        perfil = perfilSelecionado,
                        curso = curso,
                        turma = turma
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = purpleAccent),
            enabled = uiState !is AuthUiState.Loading
        ) {
            if (uiState is AuthUiState.Loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "Criar Conta e Acessar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = { navController.popBackStack() }) {
            Text(
                text = "Já possui uma conta? Fazer Login",
                color = purpleAccent,
                fontSize = 14.sp
            )
        }
    }
}
