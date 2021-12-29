package com.example.mentomenti

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log

import android.view.Menu
import android.view.MenuItem

import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mentomenti.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private var auth : FirebaseAuth? = null

    private val MatchingPage : Fragment by lazy { com.example.mentomenti.matching.MatchingPage() }
    private val ChattingPage : Fragment by lazy { com.example.mentomenti.setting.ChatFragment() }

    private val SettingPage : Fragment by lazy { com.example.mentomenti.setting.SettingPage() }

    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        if (auth?.currentUser != null) {
            Log.d(ContentValues.TAG,"dhkt"+ auth?.currentUser!!.email);
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        changeFragment(MatchingPage)
        initNavigationBar()
        val preferences = getSharedPreferences("firstCheck", MODE_PRIVATE)
        var editor = preferences.edit()
        var firstViewShow : Boolean = preferences.getBoolean("first", false)


        if (!firstViewShow) {
             editor.putBoolean("first",true).apply()
            var firstIntent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(firstIntent)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_search -> {
                //검색 버튼 눌렀을 때
                return super.onOptionsItemSelected(item)
            }
            R.id.menu_account -> {
                //계정 버튼 눌렀을 때
                return super.onOptionsItemSelected(item)
            }
            R.id.menu_logout -> {
                //로그아웃 버튼 눌렀을 때
                auth?.signOut()
                // 로그인 화면으로
                val intent = Intent(this, SignInActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}