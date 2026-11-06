package com.example.store.feature.professor.presentation.unidadescurriculares

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.feature.professor.data.repository.ProfessorRepository
import com.example.store.feature.professor.data.repository.ProfessorRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfessorUcsViewModel(
    private val repository: ProfessorRepository = ProfessorRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfessorUcsUiState>(ProfessorUcsUiState.Loading)
    val uiState: StateFlow<ProfessorUcsUiState> = _uiState.asStateFlow()

    init {
        carregarUnidadesCurriculares()
    }

    fun carregarUnidadesCurriculares() {
        _uiState.value = ProfessorUcsUiState.Loading
        viewModelScope.launch {
            repository.getUnidadesCurriculares()
                .onSuccess { ucs ->
                    _uiState.value = if (ucs.isEmpty()) {
                        ProfessorUcsUiState.Empty
                    } else {
                        ProfessorUcsUiState.Success(ucs)
                    }
                }
                .onFailure { throwable ->
                    _uiState.value = ProfessorUcsUiState.Error(
                        throwable.localizedMessage ?: "Erro ao comunicar com a API de unidades curriculares."
                    )
                }
        }
    }
}
