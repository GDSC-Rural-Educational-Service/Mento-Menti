package com.example.mentomenti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FieldClassification
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mentomenti.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private var auth : FirebaseAuth? = null

    private val MatchingPage : Fragment by lazy { com.example.mentomenti.setting.MatchingPage() }
    private val ChattingPage : Fragment by lazy { com.example.mentomenti.setting.ChattingPage() }
    private val SettingPage : Fragment by lazy { com.example.mentomenti.setting.SettingPage() }

    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        changeFragment(MatchingPage)
        initNavigationBar()
        val preferences = getSharedPreferences("firstCheck", MODE_PRIVATE)
        var editor = preferences.edit()
        var firstViewShow : Boolean = preferences.getBoolean("first", false)
        val logoutTextView: TextView = findViewById(R.id.logoutTextView)

        if (!firstViewShow) {
             editor.putBoolean("first",true).apply()
            var firstIntent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(firstIntent)
        }

        // 로그아웃
        logoutTextView.setOnClickListener {
            // 로그인 화면으로
            val intent = Intent(this, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            auth?.signOut()
        }
    }

    private fun initNavigationBar(){
        binding.bnvMain.run {
            setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.matching -> {
                        changeFragment(MatchingPage)
                    }
                    R.id.chatting -> {
                        changeFragment(ChattingPage)
                    }
                    R.id.setting -> {
                        changeFragment(SettingPage)
                    }
                }
                true
            }
        }
    }
    private fun changeFragment(fragment : Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container,fragment)
            .commit()
    }

}