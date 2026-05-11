package com.yahya.ai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ProgressBar
import kotlinx.coroutines.launch
import com.yahya.ai.network.ApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var responseText: TextView
    private lateinit var progressBar: ProgressBar
    private val apiClient = ApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)
        responseText = findViewById(R.id.responseText)
        progressBar = findViewById(R.id.progressBar)

        sendButton.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val message = messageInput.text.toString().trim()
        if (message.isEmpty()) {
            responseText.text = "❌ Lütfen bir mesaj yazın"
            return
        }

        progressBar.visibility = android.view.View.VISIBLE
        responseText.text = "⏳ Bekleniyor..."

        lifecycleScope.launch {
            try {
                val response = apiClient.sendMessage(message)
                progressBar.visibility = android.view.View.GONE
                responseText.text = response.botResponse
                messageInput.text.clear()
            } catch (e: Exception) {
                progressBar.visibility = android.view.View.GONE
                responseText.text = "❌ Hata: ${e.message}"
            }
        }
    }
}
