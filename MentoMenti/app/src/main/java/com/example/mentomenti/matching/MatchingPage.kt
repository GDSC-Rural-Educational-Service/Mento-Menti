package com.example.mentomenti.matching

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.RecyclerView
import com.example.mentomenti.R
import com.example.mentomenti.matching.CustomDialog
import com.example.mentomenti.matching.ProfileAdapter
import com.example.mentomenti.matching.ProfileData
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MatchingPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class MatchingPage : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var profileAdapter : ProfileAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_matching_page, container, false)

        val db = FirebaseFirestore.getInstance()

        profileAdapter = ProfileAdapter(container?.context)
        var profile = v.findViewById<RecyclerView>(R.id.rv_profile)
        profile.adapter = profileAdapter
        val datas = mutableListOf<ProfileData>()
        profileAdapter.datas = datas
        db.collection("mentoinfo")
            .get()
            .addOnSuccessListener { result ->
                datas.clear()
                for (document in result) {
                    val item = ProfileData(document["name"] as String,document["college"]as String,document["major"]as String)
                    datas.add(item)
                    profileAdapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener {exception->
                Log.e("failed", "Error getting documents: $exception")
            }


        profileAdapter.setItemClickListener(object: ProfileAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                var dialog: CustomDialog? = CustomDialog(context!!);
                dialog!!.start("?????? ????????? ???????????????")

            }
        })

        profileAdapter.notifyDataSetChanged()

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MatchingPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MatchingPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}