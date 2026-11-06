package com.example.store.feature.professor.data.dto

import com.google.gson.annotations.SerializedName

data class ProfessorUcDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("nome")
    val nome: String,

    @SerializedName("turmaId")
    val turmaId: Long,

    @SerializedName("turma")
    val turma: String,

    @SerializedName("cargaHoraria")
    val cargaHoraria: Int,

    @SerializedName("quantidadeAulas")
    val quantidadeAulas: Int,

    @SerializedName("aulasRealizadas")
    val aulasRealizadas: Int,

    @SerializedName("diasSemana")
    val diasSemana: List<String>,

    @SerializedName("horarioInicio")
    val horarioInicio: String,

    @SerializedName("horarioFim")
    val horarioFim: String
)
