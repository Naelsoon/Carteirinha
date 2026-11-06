package com.example.store.feature.professor.data.dto

import com.google.gson.annotations.SerializedName

data class TurmaDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("codigo")
    val codigo: String,

    @SerializedName("curso")
    val curso: String,

    @SerializedName("semestre")
    val semestre: String,

    @SerializedName("quantidadeAlunos")
    val quantidadeAlunos: Int,

    @SerializedName("diasSemana")
    val diasSemana: List<String>,

    @SerializedName("horarioInicio")
    val horarioInicio: String,

    @SerializedName("horarioFim")
    val horarioFim: String
)
