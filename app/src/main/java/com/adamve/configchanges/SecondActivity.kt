package com.adamve.configchanges

import android.content.Intent
import android.widget.Button
import android.widget.Toast

class SecondActivity : ReportingActivity(R.layout.activity_second) {
    override fun setup() {
        findViewById<Button>(R.id.main_button).setOnClickListener {
            Toast.makeText(this, R.string.text_second_button_clicked, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}