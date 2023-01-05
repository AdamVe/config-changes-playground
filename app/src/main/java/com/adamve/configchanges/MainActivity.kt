package com.adamve.configchanges

import android.content.Intent
import android.widget.Button
import android.widget.Toast

class MainActivity : ReportingActivity(R.layout.activity_main) {
    override fun setup() {
        findViewById<Button>(R.id.main_button).setOnClickListener {
            Toast.makeText(this, R.string.text_main_button_clicked, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}