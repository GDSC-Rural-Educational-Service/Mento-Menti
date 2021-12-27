package com.example.mentomenti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val join_btn = findViewById<Button>(R.id.join_button)
        join_btn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            // finish()
        }
    }
}