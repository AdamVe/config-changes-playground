package com.adamve.configchanges

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

sealed class ReportingActivity(private val layoutId: Int) : AppCompatActivity() {

    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {

        name = this.localClassName

        super.onCreate(savedInstanceState)
        log("onCreate")
        setContentView(layoutId)
        setup()
    }

    override fun onPause() {
        super.onPause()
        logCurrentConfiguration("onPause")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
    }

    private fun logCurrentConfiguration(tag: String) {
        val config = resources.configuration
        log("$tag -> KEYBOARD: ${config.keyboard} HARD_KEYBOARD_HIDDEN: ${config.hardKeyboardHidden}")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        logCurrentConfiguration("onConfigurationChanged")
    }

    fun log(message: String) {
        Log.d(name, "$name.$message")
    }

    protected open fun setup() {}
}