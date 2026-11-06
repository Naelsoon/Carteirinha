package com.example.store.feature.professor.domain.model

import com.example.store.feature.professor.data.dto.ProfessorUcDto

data class ProfessorUc(
    val id: Long,
    val nome: String,
    val turmaId: Long,
    val turma: String,
    val cargaHoraria: Int,
    val quantidadeAulas: Int,
    val aulasRealizadas: Int,
    val diasSemana: List<String>,
    val horarioInicio: String,
    val horarioFim: String
) {
    val progressoAulas: Float
        get() = if (quantidadeAulas > 0) aulasRealizadas.toFloat() / quantidadeAulas else 0f

    val percentualConcluido: Int
        get() = (progressoAulas * 100).toInt()
}

fun ProfessorUcDto.toDomain(): ProfessorUc {
    return ProfessorUc(
        id = id,
        nome = nome,
        turmaId = turmaId,
        turma = turma,
        cargaHoraria = cargaHoraria,
        quantidadeAulas = quantidadeAulas,
        aulasRealizadas = aulasRealizadas,
        diasSemana = diasSemana,
        horarioInicio = horarioInicio,
        horarioFim = horarioFim
    )
}
