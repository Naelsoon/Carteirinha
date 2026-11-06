package com.example.store.core.network.dto

import com.google.gson.annotations.SerializedName

data class RegisterRequestDto(
    @SerializedName("nome")
    val nome: String,

    @SerializedName("matricula")
    val matricula: String,

    @SerializedName("login")
    val login: String,

    @SerializedName("senha")
    val senha: String,

    @SerializedName("perfil")
    val perfil: String, // "ALUNO" ou "PROFESSOR"

    @SerializedName("curso")
    val curso: String = "",

    @SerializedName("turma")
    val turma: String = ""
)
