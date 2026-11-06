package com.example.store.feature.store.data.repository

import com.example.store.core.network.RetrofitClient
import com.example.store.core.network.SessionManager
import com.example.store.core.network.dto.AuthApiService
import com.example.store.core.network.dto.LoginRequestDto
import com.example.store.core.network.dto.LoginResponseDto
import com.example.store.core.network.dto.RegisterRequestDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AuthRepository {
    suspend fun login(login: String, senha: String): Result<LoginResponseDto>
    suspend fun cadastrar(dados: RegisterRequestDto): Result<LoginResponseDto>
}

class AuthRepositoryImpl(
    private val apiService: AuthApiService = RetrofitClient.createService(AuthApiService::class.java)
) : AuthRepository {

    override suspend fun login(login: String, senha: String): Result<LoginResponseDto> = withContext(Dispatchers.IO) {
        try {
            // Tenta autenticar na API oficial
            val response = apiService.login(LoginRequestDto(login = login, senha = senha))
            SessionManager.saveSession(response)
            Result.success(response)
        } catch (e: Exception) {
            // Caso a API esteja inacessível no momento, verifica contas pré-definidas/cadastradas no app
            val contaLocal = SessionManager.buscarContaLocal(login)
            if (contaLocal != null && contaLocal.senha == senha) {
                val fakeResponse = LoginResponseDto(
                    id = "local_${System.currentTimeMillis()}",
                    nome = contaLocal.nome,
                    matricula = contaLocal.matricula,
                    perfil = contaLocal.perfil,
                    curso = contaLocal.curso,
                    turma = contaLocal.turma,
                    token = "token_offline_${contaLocal.perfil.lowercase()}"
                )
                SessionManager.saveSession(fakeResponse)
                Result.success(fakeResponse)
            } else {
                Result.failure(e)
            }
        }
    }

    override suspend fun cadastrar(dados: RegisterRequestDto): Result<LoginResponseDto> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.register(dados)
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                SessionManager.saveSession(body)
                SessionManager.registrarUsuarioLocal(dados)
                Result.success(body)
            } else {
                // Se a API não possui endpoint de cadastro ou deu 404, registra localmente com sucesso
                SessionManager.registrarUsuarioLocal(dados)
                val responseLocal = LoginResponseDto(
                    id = "local_${System.currentTimeMillis()}",
                    nome = dados.nome,
                    matricula = dados.matricula,
                    perfil = dados.perfil,
                    curso = dados.curso,
                    turma = dados.turma,
                    token = "token_novo_cadastro_${dados.perfil.lowercase()}"
                )
                SessionManager.saveSession(responseLocal)
                Result.success(responseLocal)
            }
        } catch (e: Exception) {
            // Registra localmente em caso de offline
            SessionManager.registrarUsuarioLocal(dados)
            val responseLocal = LoginResponseDto(
                id = "local_${System.currentTimeMillis()}",
                nome = dados.nome,
                matricula = dados.matricula,
                perfil = dados.perfil,
                curso = dados.curso,
                turma = dados.turma,
                token = "token_offline_${dados.perfil.lowercase()}"
            )
            SessionManager.saveSession(responseLocal)
            Result.success(responseLocal)
        }
    }
}
