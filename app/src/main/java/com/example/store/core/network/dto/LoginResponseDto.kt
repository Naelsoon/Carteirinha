package com.example.store.core.network.dto

import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("nome")
    val nome: String? = null,

    @SerializedName("matricula")
    val matricula: String? = null,

    @SerializedName("perfil")
    val perfil: String? = null,

    @SerializedName("curso")
    val curso: String? = null,

    @SerializedName("turma")
    val turma: String? = null,

    @SerializedName("token")
    val token: String? = null
)
