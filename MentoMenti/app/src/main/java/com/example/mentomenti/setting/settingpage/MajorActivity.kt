package com.example.mentomenti.setting.settingpage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.mentomenti.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MajorActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var currentUser: String
    private var auth : FirebaseAuth? = null
    private lateinit var input1 : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major)

        supportActionBar?.title = "정보 수정하기"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        auth = Firebase.auth
        currentUser = auth?.currentUser!!.email.toString()

        input1 = findViewById<EditText>(R.id.et_major)
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
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        var map = mutableMapOf<String, Any>()
        map["major"] = input1.text.toString()
        when(item.itemId){
            R.id.fixActionBtn -> {
                db.collection("mentoinfo").document(currentUser).update(map)
                finish()
            }}
        return super.onOptionsItemSelected(item)
    }
}