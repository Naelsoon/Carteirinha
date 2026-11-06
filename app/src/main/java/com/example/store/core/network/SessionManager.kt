package com.example.store.core.network

import android.content.Context
import android.content.SharedPreferences
import com.example.store.core.network.dto.LoginResponseDto
import com.example.store.core.network.dto.RegisterRequestDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UserSession(
    val id: String = "",
    val nome: String = "",
    val matricula: String = "",
    val perfil: String = "ALUNO", // "ALUNO" ou "PROFESSOR"
    val curso: String = "",
    val turma: String = "",
    val token: String = ""
) {
    val isProfessor: Boolean
        get() = perfil.equals("PROFESSOR", ignoreCase = true)

    val isAluno: Boolean
        get() = perfil.equals("ALUNO", ignoreCase = true)
}

object SessionManager {
    private const val PREFS_SESSION = "app_session_prefs"
    private const val KEY_ID = "session_id"
    private const val KEY_NOME = "session_nome"
    private const val KEY_MATRICULA = "session_matricula"
    private const val KEY_PERFIL = "session_perfil"
    private const val KEY_CURSO = "session_curso"
    private const val KEY_TURMA = "session_turma"

    private var preferences: SharedPreferences? = null

    // Contas pré-definidas da API para permitir fallback/teste imediato
    private val contasCadastradas = mutableListOf<RegisterRequestDto>(
        RegisterRequestDto(
            nome = "Rafael Costa",
            matricula = "2026000001",
            login = "aluno",
            senha = "123",
            perfil = "ALUNO",
            curso = "Desenvolvimento de Sistemas",
            turma = "2DEVEST-A"
        ),
        RegisterRequestDto(
            nome = "Maria Oliveira",
            matricula = "2026000002",
            login = "maria",
            senha = "456",
            perfil = "ALUNO",
            curso = "Desenvolvimento de Sistemas",
            turma = "2DEVEST-B"
        ),
        RegisterRequestDto(
            nome = "Professor Modelo",
            matricula = "PROF20260001",
            login = "professor",
            senha = "123",
            perfil = "PROFESSOR",
            curso = "Desenvolvimento de Sistemas",
            turma = "2DEVEST-A"
        )
    )

    private val _currentUser = MutableStateFlow(
        UserSession(
            id = "00000000-0000-0000-0000-000000000001",
            nome = "Rafael Costa",
            matricula = "2026000001",
            perfil = "ALUNO",
            curso = "Desenvolvimento de Sistemas",
            turma = "2DEVEST-A",
            token = TokenManager.getToken()
        )
    )
    val currentUser: StateFlow<UserSession> = _currentUser.asStateFlow()

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_SESSION, Context.MODE_PRIVATE)
        val id = preferences?.getString(KEY_ID, null)
        if (id != null) {
            val nome = preferences?.getString(KEY_NOME, "Usuário") ?: "Usuário"
            val matricula = preferences?.getString(KEY_MATRICULA, "2026000001") ?: "2026000001"
            val perfil = preferences?.getString(KEY_PERFIL, "ALUNO") ?: "ALUNO"
            val curso = preferences?.getString(KEY_CURSO, "Desenvolvimento de Sistemas") ?: ""
            val turma = preferences?.getString(KEY_TURMA, "2DEVEST-A") ?: ""
            val token = TokenManager.getToken()

            _currentUser.value = UserSession(
                id = id,
                nome = nome,
                matricula = matricula,
                perfil = perfil,
                curso = curso,
                turma = turma,
                token = token
            )
        }
    }

    fun saveSession(response: LoginResponseDto) {
        val token = response.token.orEmpty()
        if (token.isNotBlank()) {
            TokenManager.saveToken(token)
        }

        val session = UserSession(
            id = response.id ?: "user_${System.currentTimeMillis()}",
            nome = response.nome ?: "Usuário",
            matricula = response.matricula ?: "2026000001",
            perfil = response.perfil ?: "ALUNO",
            curso = response.curso ?: "",
            turma = response.turma ?: "",
            token = token
        )

        _currentUser.value = session

        preferences?.edit()
            ?.putString(KEY_ID, session.id)
            ?.putString(KEY_NOME, session.nome)
            ?.putString(KEY_MATRICULA, session.matricula)
            ?.putString(KEY_PERFIL, session.perfil)
            ?.putString(KEY_CURSO, session.curso)
            ?.putString(KEY_TURMA, session.turma)
            ?.apply()
    }

    fun registrarUsuarioLocal(cadastro: RegisterRequestDto) {
        contasCadastradas.removeAll { it.login.equals(cadastro.login, ignoreCase = true) }
        contasCadastradas.add(cadastro)
    }

    fun buscarContaLocal(login: String): RegisterRequestDto? {
        return contasCadastradas.firstOrNull { it.login.equals(login, ignoreCase = true) }
    }

    fun logout() {
        TokenManager.clearToken()
        preferences?.edit()?.clear()?.apply()
        _currentUser.value = UserSession()
    }
}
