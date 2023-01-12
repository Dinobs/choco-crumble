package com.example.chococrumble.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.chococrumble.MainActivity
import com.example.chococrumble.databinding.ActivityCrashBinding
import com.example.chococrumble.utils.GlobalExceptionHandler

class CrashActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCrashBinding

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrashBinding.inflate(layoutInflater)
        textView = binding.errorMessage

        GlobalExceptionHandler.getThrowableFromIntent(intent)?.let {
            textView.text = it.cause?.message ?: "Unknown error"
        }
        setOnClickListeners()

        setContentView(binding.root)
    }

    private fun setOnClickListeners() {
        binding.retryButton.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}