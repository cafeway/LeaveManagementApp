package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.Application
import com.example.myapplication.R
import java.util.ArrayList



class RejectedAdapter(private  val context: Context, private val applications: ArrayList<Application>):
    RecyclerView.Adapter<RejectedAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    private lateinit var mlistener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClicked(positon: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mlistener = listener
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val typeOfLeave = itemView.findViewById<TextView?>(R.id.leaveTypeAdmin)
        val employeeReg = itemView.findViewById<TextView>(R.id.employeeId)
        val duration = itemView.findViewById<TextView>(R.id.leaveType2)
//        val status = itemView.findViewById<Chip>(R.id.status_chip)

        var Noteposition = 0


        init {
            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.verify_applications, parent, false)
        return ViewHolder(itemView, mlistener)
    }

    override fun getItemCount(): Int {
        return applications.size
    }

    override fun onBindViewHolder(holder: RejectedAdapter.ViewHolder, position: Int) {
        holder.duration.text = applications.get(position).Duration.toString()
        holder.typeOfLeave.text = applications[position].LeaveType
        holder.employeeReg.text = applications[position].EmployeeId
//        holder.status.text = applications[position].Status
//
//        holder.Noteposition = position


    }

}