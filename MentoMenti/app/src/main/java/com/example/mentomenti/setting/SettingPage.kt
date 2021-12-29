package com.example.mentomenti.setting

import android.content.ContentValues.TAG
import android.content.Intent
import android.media.metrics.Event
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mentomenti.R
import com.example.mentomenti.setting.settingpage.CollegeActivity
import com.example.mentomenti.setting.settingpage.EntranceActivity
import com.example.mentomenti.setting.settingpage.MajorActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingPage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var auth : FirebaseAuth? = null
    private val db = FirebaseFirestore.getInstance()
    private lateinit var currentUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        currentUser = auth?.currentUser!!.email.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_setting_page,container,false)
        var college = v.findViewById<LinearLayout>(R.id.set_college)
        var major = v.findViewById<LinearLayout>(R.id.set_major)
        var extrance = v.findViewById<LinearLayout>(R.id.set_extrance)

        var name = v.findViewById<TextView>(R.id.infoTv)
        var pcollege = v.findViewById<TextView>(R.id.cWeight)
        var pmajor = v.findViewById<TextView>(R.id.tWeight)
        var pextrance = v.findViewById<TextView>(R.id.recokcal)

        var me = db.collection("mentoinfo").document(currentUser)
        Log.d(TAG, "o" + me.toString())
        if(me != null){
            me.addSnapshotListener(EventListener<DocumentSnapshot>{ snapshot, e->
                if(snapshot!= null && snapshot.exists()){
                    Log.d(TAG, "kk"+me.toString())
                    pcollege.text = snapshot.data!!["college"].toString()
                    pmajor.text = snapshot.data!!["major"].toString()
                    pextrance.text = snapshot.data!!["way"].toString()
                    name.text = snapshot.data!!["name"].toString()
                }
            })
        }
        else{
            me = db.collection("mentiinfo").document(currentUser)
            Log.d(TAG, "dd"+ me.toString())
            if(me != null){
                me.addSnapshotListener(EventListener<DocumentSnapshot>{ snapshot, e->
                    if(snapshot!= null && snapshot.exists()){
                        Log.d(TAG, "ss" + me.toString())
                        pcollege.text = snapshot.data!!["college"].toString()
                        pmajor.text = snapshot.data!!["major"].toString()
                        pextrance.text = snapshot.data!!["way"].toString()
                        name.text = snapshot.data!!["name"].toString()
                    }
                })
            }
        }
        college.setOnClickListener {
            val intent = Intent(context, CollegeActivity::class.java)
            startActivity(intent)
        }
        major.setOnClickListener {
            val intent = Intent(context, MajorActivity::class.java)
            startActivity(intent)
        }
        extrance.setOnClickListener {
            val intent = Intent(context, EntranceActivity::class.java)
            startActivity(intent)
        }

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}