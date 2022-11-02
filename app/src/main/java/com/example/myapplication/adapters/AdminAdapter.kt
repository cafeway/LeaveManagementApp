package com.example.myapplication.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.Application
import com.example.myapplication.R
import com.google.android.material.chip.Chip
import java.util.*

class AdminAdapter(private  val context: Context, private val applications: ArrayList<Application>):
    RecyclerView.Adapter<AdminAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    private lateinit var mlistener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClicked(positon: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mlistener= listener
    }
    class ViewHolder(itemView: View,listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView){
//        val duration = itemView.findViewById<TextView?>(R.id.duration)
//        val applicationDate = itemView.findViewById<TextView>(R.id.applicationDate)
//        val type = itemView.findViewById<TextView>(R.id.leaveType)
//        val status = itemView.findViewById<Chip>(R.id.status_chip)
//
//        var Noteposition = 0
//
//        init {
//            itemView.setOnClickListener{
//                listener.onItemClicked(adapterPosition)
//            }
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =  layoutInflater.inflate(R.layout.content_login,parent,false)
        return ViewHolder(itemView,mlistener)
    }
    override fun getItemCount(): Int {
        return  applications.size
    }

    override fun onBindViewHolder(holder: AdminAdapter.ViewHolder, position: Int) {
//        holder.duration.text = applications.get(position).Duration
//        holder.applicationDate.text = applications[position].Date
//        holder.type.text = applications[position].Type
//        holder.status.text = applications[position].Status
//
//        holder.Noteposition = position

    }

    fun setOnItemClickListener(listener: AdminAdapter.OnItemClickListener, function: () -> Unit) {

    }

}