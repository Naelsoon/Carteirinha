package com.example.store.feature.professor.presentation.turmas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.feature.professor.data.repository.ProfessorRepository
import com.example.store.feature.professor.data.repository.ProfessorRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TurmasViewModel(
    private val repository: ProfessorRepository = ProfessorRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<TurmasUiState>(TurmasUiState.Loading)
    val uiState: StateFlow<TurmasUiState> = _uiState.asStateFlow()

    init {
        carregarTurmas()
    }

    fun carregarTurmas() {
        _uiState.value = TurmasUiState.Loading
        viewModelScope.launch {
            repository.getTurmas()
                .onSuccess { turmas ->
                    _uiState.value = if (turmas.isEmpty()) {
                        TurmasUiState.Empty
                    } else {
                        TurmasUiState.Success(turmas)
                    }
                }
                .onFailure { throwable ->
                    _uiState.value = TurmasUiState.Error(
                        throwable.localizedMessage ?: "Erro ao comunicar com a API de turmas."
                    )
                }
        }
    }
}
