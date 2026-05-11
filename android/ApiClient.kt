package com.yahya.ai.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

data class ChatResponse(
    val userMessage: String,
    val botResponse: String,
    val timestamp: String
)

class ApiClient {
    private val client = OkHttpClient()
    private val baseUrl = "http://192.168.1.100:5000"  // IP'nizi yazın

    suspend fun sendMessage(message: String): ChatResponse {
        val json = JSONObject().apply {
            put("message", message)
        }

        val requestBody = json.toString()
            .toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("$baseUrl/api/chat")
            .post(requestBody)
            .build()

        return client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw Exception("Sunucu hatası: ${response.code}")

            val responseJson = JSONObject(response.body?.string() ?: "{}")
            ChatResponse(
                userMessage = responseJson.getString("user_message"),
                botResponse = responseJson.getString("bot_response"),
                timestamp = responseJson.getString("timestamp")
            )
        }
    }
}
