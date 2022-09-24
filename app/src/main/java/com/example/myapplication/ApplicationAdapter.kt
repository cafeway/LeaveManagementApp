package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApplicationAdapter (val context:Context,val applications: List<Application>):
RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder>(){
    class ApplicationViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val duration = itemView.findViewById<TextView>(R.id.duration)
        val applicationDate = itemView.findViewById<TextView>(R.id.apply)
        val leaveType = itemView.findViewById<TextView>(R.id.leaveType)
//        val status = itemView.findViewById<TextView>(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.content_login, parent, false)
        return  ApplicationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApplicationViewHolder, position: Int) {
        val application = applications[position]
        holder.duration.text = application.Duration
        holder.leaveType.text = application.Type
        holder.applicationDate.text = application.Date
//        holder.status.text = application.Status
    }

    override fun getItemCount(): Int {
        return applications.size
    }
}
