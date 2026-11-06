package com.example.store.core.network.dto

import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    @SerializedName("login")
    val login: String,

    @SerializedName("senha")
    val senha: String
)
