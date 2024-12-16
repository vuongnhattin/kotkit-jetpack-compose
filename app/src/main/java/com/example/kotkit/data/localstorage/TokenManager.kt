package com.example.kotkit.data.localstorage

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import org.json.JSONObject

class TokenManager(private val sharedPreferences: SharedPreferences) {
    private val jwtKey = "jwt_token"

    fun getToken(): String? {
        return sharedPreferences.getString(jwtKey, null)
    }

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(jwtKey, token)
        editor.apply() // Use apply for asynchronous saving
    }

    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove(jwtKey) // Removes the token
        editor.apply()
    }

    private fun decode(token: String): JSONObject? {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) throw IllegalArgumentException("Invalid JWT token")

            val payload = parts[1]
            val decodedBytes = Base64.decode(payload, Base64.URL_SAFE)
            val decodedString = String(decodedBytes)
            JSONObject(decodedString) // Convert payload to JSON object
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getCurrentUsername(): String {
        val jwt = getToken() ?: return ""
        val jwtDecoded = decode(jwt)
        return jwtDecoded?.getString("sub")!!
    }
}