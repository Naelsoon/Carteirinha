package com.example.store.feature.professor.presentation.turmas

import com.example.store.feature.professor.domain.model.Turma

sealed interface TurmasUiState {
    data object Loading : TurmasUiState
    data object Empty : TurmasUiState
    data class Success(val turmas: List<Turma>) : TurmasUiState
    data class Error(val message: String) : TurmasUiState
}
