package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApplicationAdapter (private  val context: Context,private val applications: MutableList<Application>):
    RecyclerView.Adapter<ApplicationAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val duration = itemView.findViewById<TextView?>(R.id.duration)
        val applicationDate = itemView.findViewById<TextView>(R.id.applicationDate)
        val type = itemView.findViewById<TextView>(R.id.leaveType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =  layoutInflater.inflate(R.layout.content_login,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.duration.text = applications[position].Duration
        holder.applicationDate.text = applications[position].Date
        holder.type.text = applications[position].Type
    }

    override fun getItemCount(): Int {
        return  applications.size
    }
}