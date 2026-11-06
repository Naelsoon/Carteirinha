package com.example.store.feature.unidadecurriculares.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class UnidadeCurricularAlunoDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("nome")
    val nome: String,

    @SerializedName("professor")
    val professor: String,

    @SerializedName("nota1")
    val nota1: Double,

    @SerializedName("nota2")
    val nota2: Double,

    @SerializedName("media")
    val media: Double,

    @SerializedName("faltas")
    val faltas: Int
)

interface AlunoApiService {
    @GET("unidades-curriculares")
    suspend fun getUnidadesCurriculares(): List<UnidadeCurricularAlunoDto>
}
