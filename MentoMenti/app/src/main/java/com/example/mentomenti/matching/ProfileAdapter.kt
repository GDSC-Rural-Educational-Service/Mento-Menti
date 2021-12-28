package com.example.mentomenti.matching

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mentomenti.R
import com.example.mentomenti.matching.MatchingPage

class ProfileAdapter(private val context : Context?) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){
    var datas = mutableListOf<ProfileData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.profile_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    private lateinit var itemClickListener : OnItemClickListener
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name: TextView = itemView.findViewById(R.id.profile_name)
        private val college: TextView = itemView.findViewById(R.id.profile_college)
        private val major: TextView = itemView.findViewById(R.id.profile_major)

        fun bind(item: ProfileData) {
            name.text = item.name
            college.text = item.college
            major.text = item.major

        }
    }

}