package com.adamve.configchanges

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")
        setContentView(R.layout.activity_main)
        setup()
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        log("onConfigurationChanged")
    }

    private fun setup() {
        findViewById<Button>(R.id.main_button).setOnClickListener {
            Toast.makeText(this, R.string.text_main_button_clicked, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    private fun log(message: String) {
        Log.d("MainActivity", "MainActivity.$message")
    }

}