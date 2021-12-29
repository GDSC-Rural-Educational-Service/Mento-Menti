package com.example.mentomenti

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.title = "회원가입"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        val db = Firebase.firestore

        val email = findViewById<EditText>(R.id.join_email)
        val password = findViewById<EditText>(R.id.join_password)
        val role = findViewById<RadioGroup>(R.id.join_role)
        val name = findViewById<EditText>(R.id.join_name)
        val college = findViewById<EditText>(R.id.join_college)
        val major = findViewById<EditText>(R.id.join_major)
        val way = findViewById<RadioGroup>(R.id.join_way)
        val susi = findViewById<RadioButton>(R.id.join_susi)
        val jeongsi = findViewById<RadioButton>(R.id.join_jeongsi)


        val join_btn = findViewById<Button>(R.id.join_button)
        join_btn.setOnClickListener {

            // +로그인 정보 저장하는 로직 추가해야함
            // + Hashmap 미숙해서 반복되는 코드가 많음
            if (role.checkedRadioButtonId == R.id.join_mento) {
                if (way.checkedRadioButtonId == R.id.join_susi) {
                    val mento = hashMapOf(
                        "name" to name.text.toString(),
                        "college" to college.text.toString(),
                        "major" to major.text.toString(),
                        "way" to susi.text.toString()
                    )
                    db.collection("mentoinfo").document(email.text.toString())
                        .set(mento)
                        .addOnSuccessListener { Log.d("성공","Success") }
                        .addOnFailureListener { Log.e("실패","Failed")}
                }
                else {
                    val mento = hashMapOf(
                        "name" to name.text.toString(),
                        "college" to college.text.toString(),
                        "major" to major.text.toString(),
                        "way" to jeongsi.text.toString()
                    )
                    db.collection("mentoinfo").document(email.text.toString())
                        .set(mento)
                        .addOnSuccessListener { Log.d("성공","Success") }
                        .addOnFailureListener { Log.e("실패","Failed")}
                }

            } else {
                if (way.checkedRadioButtonId == R.id.join_susi) {
                    val menti = hashMapOf(
                        "name" to name.text.toString(),
                        "college" to college.text.toString(),
                        "major" to major.text.toString(),
                        "way" to susi.text.toString()
                    )
                    db.collection("mentiinfo").document(email.text.toString())
                        .set(menti)
                        .addOnSuccessListener { Log.d("성공","Success") }
                        .addOnFailureListener { Log.e("실패","Failed")}
                }
                else {
                    val menti = hashMapOf(
                        "name" to name.text.toString(),
                        "college" to college.text.toString(),
                        "major" to major.text.toString(),
                        "way" to jeongsi.text.toString()
                    )
                    db.collection("mentiinfo").document(email.text.toString())
                        .set(menti)
                        .addOnSuccessListener { Log.d("성공","Success") }
                        .addOnFailureListener { Log.e("실패","Failed")}
                }

            }

            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}