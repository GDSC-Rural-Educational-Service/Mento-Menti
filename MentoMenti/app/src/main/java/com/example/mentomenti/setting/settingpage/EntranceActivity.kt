package com.example.mentomenti.setting.settingpage

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.mentomenti.R

class EntranceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

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
        when(item.itemId){
            R.id.fixActionBtn -> {
                finish()
            }}
        return super.onOptionsItemSelected(item)
    }
}