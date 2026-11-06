package com.example.store.feature.professor.domain.model

import com.example.store.feature.professor.data.dto.TurmaDto

data class Turma(
    val id: Long,
    val codigo: String,
    val curso: String,
    val semestre: String,
    val quantidadeAlunos: Int,
    val diasSemana: List<String>,
    val horarioInicio: String,
    val horarioFim: String
)

fun TurmaDto.toDomain(): Turma {
    return Turma(
        id = id,
        codigo = codigo,
        curso = curso,
        semestre = semestre,
        quantidadeAlunos = quantidadeAlunos,
        diasSemana = diasSemana,
        horarioInicio = horarioInicio,
        horarioFim = horarioFim
    )
}
