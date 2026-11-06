package com.example.store.feature.professor.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.store.core.network.RetrofitClient
import com.example.store.feature.unidadecurriculares.data.remote.AlunoApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.domain.model.UnidadeCurricular
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object NotasRepository {
    private const val PREFS_NAME = "notas_compartilhadas_prefs"
    private const val KEY_NOTAS_JSON = "key_notas_ucs"

    private var preferences: SharedPreferences? = null
    private val gson = Gson()
    private val apiService: AlunoApiService by lazy {
        RetrofitClient.createService(AlunoApiService::class.java)
    }

    // Lista inicial padrão baseada no backend e na aula
    private val listaPadrao = listOf(
        UnidadeCurricular(
            id = "10000000-0000-0000-0000-000000000001",
            nome = "Banco de Dados",
            professor = "Carlos Silva",
            nota1 = 8.5,
            nota2 = 9.0,
            media = 8.75,
            faltas = 1
        ),
        UnidadeCurricular(
            id = "10000000-0000-0000-0000-000000000002",
            nome = "Programação para Dispositivos Móveis",
            professor = "Ana Souza",
            nota1 = 9.0,
            nota2 = 8.0,
            media = 8.5,
            faltas = 2
        ),
        UnidadeCurricular(
            id = "10000000-0000-0000-0000-000000000003",
            nome = "Desenvolvimento de Sistemas",
            professor = "Marcos Lima",
            nota1 = 7.5,
            nota2 = 8.5,
            media = 8.0,
            faltas = 0
        ),
        UnidadeCurricular(
            id = "10000000-0000-0000-0000-000000000004",
            nome = "Testes de Software",
            professor = "Juliana Alves",
            nota1 = 8.0,
            nota2 = 9.5,
            media = 8.75,
            faltas = 1
        )
    )

    private val _ucsState = MutableStateFlow<List<UnidadeCurricular>>(listaPadrao)
    val ucsState: StateFlow<List<UnidadeCurricular>> = _ucsState.asStateFlow()

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val jsonSalvo = preferences?.getString(KEY_NOTAS_JSON, null)
        if (!jsonSalvo.isNullOrBlank()) {
            try {
                val type = object : TypeToken<List<UnidadeCurricular>>() {}.type
                val listaSalva: List<UnidadeCurricular> = gson.fromJson(jsonSalvo, type)
                if (listaSalva.isNotEmpty()) {
                    _ucsState.value = listaSalva
                }
            } catch (e: Exception) {
                _ucsState.value = listaPadrao
            }
        }
    }

    /**
     * Atualiza as notas e faltas de uma Unidade Curricular lançadas pelo Professor.
     * Recalcula a média automaticamente e emite a atualização para o Aluno.
     */
    fun lancarNota(
        ucId: String,
        novaNota1: Double,
        novaNota2: Double,
        novasFaltas: Int
    ) {
        val mediaCalculada = ((novaNota1 + novaNota2) / 2.0 * 100).toInt() / 100.0
        val listaAtual = _ucsState.value.toMutableList()

        val index = listaAtual.indexOfFirst { it.id == ucId }
        if (index >= 0) {
            val ucAntiga = listaAtual[index]
            listaAtual[index] = ucAntiga.copy(
                nota1 = novaNota1,
                nota2 = novaNota2,
                media = mediaCalculada,
                faltas = novasFaltas
            )
        } else {
            // Se não encontrou por ID, tenta por nome aproximado
            val indexPorNome = listaAtual.indexOfFirst {
                it.nome.contains(ucId, ignoreCase = true) || ucId.contains(it.nome, ignoreCase = true)
            }
            if (indexPorNome >= 0) {
                val ucAntiga = listaAtual[indexPorNome]
                listaAtual[indexPorNome] = ucAntiga.copy(
                    nota1 = novaNota1,
                    nota2 = novaNota2,
                    media = mediaCalculada,
                    faltas = novasFaltas
                )
            }
        }

        _ucsState.value = listaAtual
        salvarNoStorage(listaAtual)
    }

    /**
     * Tenta sincronizar com a API oficial caso o aluno esteja autenticado
     */
    suspend fun sincronizarComApi() = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getUnidadesCurriculares()
            if (response.isNotEmpty()) {
                val listaMapeada = response.map { dto ->
                    UnidadeCurricular(
                        id = dto.id,
                        nome = dto.nome,
                        professor = dto.professor,
                        nota1 = dto.nota1,
                        nota2 = dto.nota2,
                        media = dto.media,
                        faltas = dto.faltas
                    )
                }
                _ucsState.value = listaMapeada
                salvarNoStorage(listaMapeada)
            }
        } catch (_: Exception) {
            // Mantém os dados locais/atualizados pelo professor caso a API esteja offline
        }
    }

    private fun salvarNoStorage(lista: List<UnidadeCurricular>) {
        preferences?.edit()?.putString(KEY_NOTAS_JSON, gson.toJson(lista))?.apply()
    }
}
