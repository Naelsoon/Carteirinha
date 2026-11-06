package com.example.store.core.network

import android.content.Context
import android.content.SharedPreferences

object TokenManager {
    private const val PREF_NAME = "auth_prefs"
    private const val KEY_TOKEN = "jwt_token"

    private var inMemoryToken: String = "token_de_teste_professor_2026"
    private var preferences: SharedPreferences? = null

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        preferences?.getString(KEY_TOKEN, null)?.let {
            if (it.isNotBlank()) {
                inMemoryToken = it
            }
        }
    }

    fun saveToken(token: String) {
        inMemoryToken = token
        preferences?.edit()?.putString(KEY_TOKEN, token)?.apply()
    }

    fun getToken(): String {
        return preferences?.getString(KEY_TOKEN, inMemoryToken) ?: inMemoryToken
    }

    fun clearToken() {
        inMemoryToken = ""
        preferences?.edit()?.remove(KEY_TOKEN)?.apply()
    }
}
