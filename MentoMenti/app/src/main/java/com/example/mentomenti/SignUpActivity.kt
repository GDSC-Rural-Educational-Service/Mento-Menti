package com.example.mentomenti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val join_btn = findViewById<Button>(R.id.join_button)
        join_btn.setOnClickListener {
            finish()
        }
    }
}