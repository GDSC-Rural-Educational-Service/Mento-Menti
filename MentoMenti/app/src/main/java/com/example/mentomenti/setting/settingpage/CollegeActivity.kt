package com.example.mentomenti.setting.settingpage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.mentomenti.R
import com.google.firebase.firestore.FirebaseFirestore

class CollegeActivity : AppCompatActivity() {

    private lateinit var fbFirestore : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_college)

        supportActionBar?.title = "정보 수정하기"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        fbFirestore = FirebaseFirestore.getInstance()

        val input1 = findViewById<EditText>(R.id.et_major)
        var imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(input1,0)
        input1.requestFocus()


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.setting_detail, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when(item.itemId){
            R.id.fixActionBtn -> {

                finish()
            }}
        return super.onOptionsItemSelected(item)
    }
}