package com.example.chococrumble

import android.app.Application
import com.example.chococrumble.ui.CrashActivity
import com.example.chococrumble.utils.GlobalExceptionHandler

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalExceptionHandler.initialize(this, CrashActivity::class.java)
    }
}