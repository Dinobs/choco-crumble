package com.example.chococrumble.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import java.lang.Exception
import kotlin.system.exitProcess

class GlobalExceptionHandler private constructor(
    private val applicationContext: Context,
    private val defaultHandler: Thread.UncaughtExceptionHandler,
    private val activityToBeLaunched: Class<*>
) : Thread.UncaughtExceptionHandler {
    companion object {
        private const val INTENT_DATA_NAME = "CrashData"
        private const val TAG = "CustomExceptionHandler"

        fun initialize(applicationContext: Context, activityToBeLaunched: Class<*>) {
            val handler = GlobalExceptionHandler(
                applicationContext,
                Thread.getDefaultUncaughtExceptionHandler() as Thread.UncaughtExceptionHandler,
                activityToBeLaunched
            )
            Thread.setDefaultUncaughtExceptionHandler(handler)
        }

        fun getThrowableFromIntent(intent: Intent): Throwable? {
            return try {
                Gson().fromJson(intent.getStringExtra(INTENT_DATA_NAME), Throwable::class.java)
            } catch (e:Exception) {
                Log.e(TAG, "getThrowableFromIntent: ", e)
                null
            }
        }
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        try {
            launchActivity(applicationContext, activityToBeLaunched, throwable)
            exitProcess(0)
        } catch (e: Exception) {
            defaultHandler.uncaughtException(thread, throwable)
        }
    }

    private fun launchActivity(applicationContext: Context, activity: Class<*>, exception: Throwable) {
        val crashedIntent = Intent(applicationContext, activity).also {
            it.putExtra(INTENT_DATA_NAME, Gson().toJson(exception))
        }
        crashedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        crashedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        applicationContext.startActivity(crashedIntent)
    }
}