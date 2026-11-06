package com.example.store.feature.professor.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.feature.professor.data.repository.NotasRepository

private val cardBg = Color(0xFF1F222A)
private val purpleAccent = Color(0xFF8A2BE2)
private val textMuted = Color(0xFF9E9E9E)

@Composable
fun LancarNotasDialog(
    disciplinaNome: String,
    ucId: String,
    nota1Atual: Double = 8.0,
    nota2Atual: Double = 8.0,
    faltasAtual: Int = 0,
    onDismiss: () -> Unit,
    onSalvo: () -> Unit
) {
    var nota1Text by remember { mutableStateOf(nota1Atual.toString()) }
    var nota2Text by remember { mutableStateOf(nota2Atual.toString()) }
    var faltasText by remember { mutableStateOf(faltasAtual.toString()) }

    val n1 = nota1Text.toDoubleOrNull() ?: 0.0
    val n2 = nota2Text.toDoubleOrNull() ?: 0.0
    val faltas = faltasText.toIntOrNull() ?: 0
    val mediaCalculada = ((n1 + n2) / 2.0 * 100).toInt() / 100.0

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = cardBg,
        shape = RoundedCornerShape(16.dp),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.EditNote,
                    contentDescription = null,
                    tint = purpleAccent,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Lançar Notas do Aluno",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Disciplina: $disciplinaNome",
                    color = purpleAccent,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )

                Text(
                    text = "As alterações inseridas aqui atualizarão o boletim do aluno em tempo real.",
                    color = textMuted,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = nota1Text,
                        onValueChange = { nota1Text = it },
                        label = { Text("Nota 1") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color(0xFF14161C),
                            unfocusedContainerColor = Color(0xFF14161C),
                            focusedIndicatorColor = purpleAccent,
                            unfocusedIndicatorColor = textMuted
                        )
                    )

                    OutlinedTextField(
                        value = nota2Text,
                        onValueChange = { nota2Text = it },
                        label = { Text("Nota 2") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color(0xFF14161C),
                            unfocusedContainerColor = Color(0xFF14161C),
                            focusedIndicatorColor = purpleAccent,
                            unfocusedIndicatorColor = textMuted
                        )
                    )
                }

                OutlinedTextField(
                    value = faltasText,
                    onValueChange = { faltasText = it },
                    label = { Text("Total de Faltas") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color(0xFF14161C),
                        unfocusedContainerColor = Color(0xFF14161C),
                        focusedIndicatorColor = purpleAccent,
                        unfocusedIndicatorColor = textMuted
                    )
                )

                // Box com o cálculo da Média
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF2B2245), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Calculate,
                                contentDescription = null,
                                tint = purpleAccent
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Média Final:",
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }

                        Text(
                            text = "$mediaCalculada",
                            color = purpleAccent,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    NotasRepository.lancarNota(
                        ucId = ucId.ifBlank { disciplinaNome },
                        novaNota1 = n1,
                        novaNota2 = n2,
                        novasFaltas = faltas
                    )
                    onSalvo()
                },
                colors = ButtonDefaults.buttonColors(containerColor = purpleAccent),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Salvar Notas", color = Color.White)
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onDismiss,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, textMuted)
            ) {
                Text("Cancelar", color = Color.White)
            }
        }
    )
}
