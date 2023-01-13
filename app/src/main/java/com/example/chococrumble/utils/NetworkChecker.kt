package com.example.chococrumble.utils

import android.content.Context
import android.net.ConnectivityManager
import java.lang.Exception

class NetworkChecker {
    companion object {
        private fun isInternetActive(context: Context): Boolean {
            val manager = context.getSystemService(ConnectivityManager::class.java)
            val currentNetwork = manager.activeNetwork

            return currentNetwork != null
        }

        fun checkInternetConnection(context: Context) {
            if (!isInternetActive(context)) {
                throw Exception ("No internet connection")
            }
        }
    }
}