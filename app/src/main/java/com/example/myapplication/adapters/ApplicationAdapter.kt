package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.Application
import com.example.myapplication.R
import com.google.android.material.chip.Chip

class ApplicationAdapter (private  val context: Context,private val applications: ArrayList<Application>):
    RecyclerView.Adapter<ApplicationAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val duration = itemView.findViewById<TextView?>(R.id.duration)
        val applicationDate = itemView.findViewById<TextView>(R.id.applicationDate)
        val type = itemView.findViewById<TextView>(R.id.LeaveType)
        val status = itemView.findViewById<TextView>(R.id.duration)
        val end_date = itemView.findViewById<TextView>(R.id.leaveType2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =  layoutInflater.inflate(R.layout.content_login,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.applicationDate.text = applications.get(position).LeaveType
        holder.type.text = applications.get(position).Department
        holder.status.text = applications.get(position).Duration.toString()
        holder.end_date.text = applications.get(position).StartDate
    }

    override fun getItemCount(): Int {
        return  applications.size
    }
}