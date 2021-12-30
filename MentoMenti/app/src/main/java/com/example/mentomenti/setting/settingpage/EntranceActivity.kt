package com.example.mentomenti.setting.settingpage

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.mentomenti.MainActivity
import com.example.mentomenti.R
import com.example.mentomenti.setting.SettingPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class EntranceActivity : AppCompatActivity() {
    var way : String = ""
    private lateinit var fbFirestore : FirebaseFirestore
    private val db = FirebaseFirestore.getInstance()
    private lateinit var currentUser: String
    private var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        auth = Firebase.auth
        currentUser = auth?.currentUser!!.email.toString()
        fbFirestore = FirebaseFirestore.getInstance()

        supportActionBar?.title = "정보 수정하기"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        val susi = findViewById<AppCompatButton>(R.id.susi)
        val jeongsi = findViewById<AppCompatButton>(R.id.jeongsi)

        var clicked : Boolean = false
        var idCheck : AppCompatButton?= null
        var gender : Int = 0

        susi.setOnClickListener {
            if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
                idCheck = susi
                clicked = true
                way = "susi"
                susi.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_unclicked))
                susi.setTextColor(Color.rgb(92,196,133))
            }
            else if (idCheck == susi) {
                clicked = false
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
            }
            else { // 다른거 클릭 되있음
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
                idCheck = susi
                susi.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_unclicked))
                susi.setTextColor(Color.rgb(92,196,133))
            }
        }

        jeongsi.setOnClickListener {
            if (idCheck == null || clicked == false){ // 아무 것도 클릭 안되어 있음
                idCheck = jeongsi
                clicked = true
                way = "jeongsi"
                jeongsi.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_unclicked))
                jeongsi.setTextColor(Color.rgb(92,196,133))
            }
            else if (idCheck == jeongsi) {
                clicked = false
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
            }
            else { // 다른거 클릭 되있음
                idCheck!!.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_background))
                idCheck!!.setTextColor(Color.rgb(102,102,102))
                idCheck = jeongsi
                jeongsi.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.btn_unclicked))
                jeongsi.setTextColor(Color.rgb(92,196,133))
            }
        }
        if (idCheck == susi) {
            gender = 0
        }
        else if(idCheck == jeongsi) {
            gender = 1
        }
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
        map["way"] = ""
        if(way == "susi") {
            map["way"] = "수시"
        } else if(way == "jeongsi"){
            map["way"] = "정시"
        } else {
            Toast.makeText(
                baseContext, "수시, 정시를 선택해주세요!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        when(item.itemId){
            R.id.fixActionBtn -> {
                db.collection("mentoinfo").document(currentUser).update(map)
                finish()
            }}
        return super.onOptionsItemSelected(item)
    }

}