package com.example.store.feature.professor.data.remote

import com.example.store.feature.professor.data.dto.ProfessorUcDto
import com.example.store.feature.professor.data.dto.TurmaDto
import retrofit2.http.GET

interface ProfessorApiService {

    @GET("professores/me/turmas")
    suspend fun getTurmas(): List<TurmaDto>

    @GET("professores/me/unidades-curriculares")
    suspend fun getUnidadesCurriculares(): List<ProfessorUcDto>
}
