package com.example.store.feature.store.presetation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.network.dto.LoginResponseDto
import com.example.store.core.network.dto.RegisterRequestDto
import com.example.store.feature.store.data.repository.AuthRepository
import com.example.store.feature.store.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AuthUiState {
    data object Idle : AuthUiState
    data object Loading : AuthUiState
    data class Success(val response: LoginResponseDto) : AuthUiState
    data class Error(val message: String) : AuthUiState
}

class AuthViewModel(
    private val repository: AuthRepository = AuthRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(login: String, senha: String) {
        if (login.isBlank() || senha.isBlank()) {
            _uiState.value = AuthUiState.Error("Por favor, preencha o login e a senha.")
            return
        }

        _uiState.value = AuthUiState.Loading
        viewModelScope.launch {
            repository.login(login.trim(), senha.trim())
                .onSuccess { response ->
                    _uiState.value = AuthUiState.Success(response)
                }
                .onFailure { error ->
                    _uiState.value = AuthUiState.Error(
                        error.localizedMessage ?: "Credenciais inválidas ou erro ao conectar com o servidor."
                    )
                }
        }
    }

    fun cadastrar(dados: RegisterRequestDto) {
        if (dados.nome.isBlank() || dados.login.isBlank() || dados.senha.isBlank()) {
            _uiState.value = AuthUiState.Error("Preencha todos os campos obrigatórios.")
            return
        }

        _uiState.value = AuthUiState.Loading
        viewModelScope.launch {
            repository.cadastrar(dados)
                .onSuccess { response ->
                    _uiState.value = AuthUiState.Success(response)
                }
                .onFailure { error ->
                    _uiState.value = AuthUiState.Error(
                        error.localizedMessage ?: "Erro ao realizar cadastro."
                    )
                }
        }
    }

    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
}
