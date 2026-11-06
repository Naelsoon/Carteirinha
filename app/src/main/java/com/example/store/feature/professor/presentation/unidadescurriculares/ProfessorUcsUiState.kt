package com.example.store.feature.professor.presentation.unidadescurriculares

import com.example.store.feature.professor.domain.model.ProfessorUc

sealed interface ProfessorUcsUiState {
    data object Loading : ProfessorUcsUiState
    data object Empty : ProfessorUcsUiState
    data class Success(val ucs: List<ProfessorUc>) : ProfessorUcsUiState
    data class Error(val message: String) : ProfessorUcsUiState
}
