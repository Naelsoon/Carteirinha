package com.example.store.feature.professor.data.repository

import com.example.store.core.network.RetrofitClient
import com.example.store.feature.professor.data.remote.ProfessorApiService
import com.example.store.feature.professor.domain.model.ProfessorUc
import com.example.store.feature.professor.domain.model.Turma
import com.example.store.feature.professor.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ProfessorRepository {
    suspend fun getTurmas(): Result<List<Turma>>
    suspend fun getUnidadesCurriculares(): Result<List<ProfessorUc>>
}

class ProfessorRepositoryImpl(
    private val apiService: ProfessorApiService = RetrofitClient.createService(ProfessorApiService::class.java)
) : ProfessorRepository {

    override suspend fun getTurmas(): Result<List<Turma>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getTurmas()
            val turmas = response.map { it.toDomain() }
            Result.success(turmas)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUnidadesCurriculares(): Result<List<ProfessorUc>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getUnidadesCurriculares()
            val ucs = response.map { it.toDomain() }
            Result.success(ucs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
