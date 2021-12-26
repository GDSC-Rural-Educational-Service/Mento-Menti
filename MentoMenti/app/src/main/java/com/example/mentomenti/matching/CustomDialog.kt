package com.example.mentomenti.matching

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mentomenti.R

class CustomDialog(context: Context) : AppCompatActivity() {

    private val dialog = Dialog(context)
    private lateinit var body : TextView
    private lateinit var confirm : Button
    private lateinit var cancel : Button
    val TAG = "로그 "

    fun start(bodyMessage : String, mode : Int) {
        //dialogMode = 2 (확인 버튼과 취소 버튼 존재함.)
        //dialogMode = 1 (확인 버튼만 존재함)
        //mode변수값에 따라 다르게 클릭 리스너 이벤트 활동이 달라짐 (소스참고)
        //mode 값에 따라서 클릭리스너를 셋팅합니다!

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //여백을 제거함( 이부분은 주석처리하고 어떻게 다이얼로그가 생성되는지 보는게 이해하는게 가장 빠름) ex) TRANSPARENT값은 투명함.

        dialog.setContentView(R.layout.matching_dialog)
        body = dialog.findViewById(R.id.body)
        body.setText(bodyMessage)

        confirm = dialog.findViewById(R.id.Confirm)
        cancel = dialog.findViewById(R.id.cancel)

        cancel.setOnClickListener{ //취소버튼

            when(mode){
                1 -> {
                    dialog.dismiss()
                }
                2 ->{
                    dialog.dismiss()
                }
            }
        }

        confirm.setOnClickListener { //확인버튼
            when(mode){
                1 -> {

                }
                2 -> {

                }
            }
        }

        dialog.setCancelable(false)    //다이얼로그 이외의 화면 터치시 닫히지 않음!
        dialog.show()
    }
}