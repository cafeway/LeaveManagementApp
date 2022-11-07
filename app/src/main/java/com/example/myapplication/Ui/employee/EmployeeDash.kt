package com.example.myapplication.Ui.employee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.Application
import com.example.myapplication.Models.GetResponse
import com.example.myapplication.R
import com.example.myapplication.adapters.ApplicationAdapter
import com.example.myapplication.interfaces.EmployeeInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EmployeeDash : AppCompatActivity() {

    lateinit var adapter: ApplicationAdapter
    lateinit var applicationRV: RecyclerView
    lateinit var applicationList: ArrayList<Application>


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_dash)

        // get the email passed in intents
        val bundle: Bundle? = intent.extras
        val a = bundle?.get("Email")
        var mail = a.toString()
        Log.d("email","$a")
        //get icons via id
        var apply_icon = findViewById<ImageView>(R.id.apply_icon)
        var pending_icon = findViewById<ImageView>(R.id.accomodation_icon)
        var rejected_icon = findViewById<ImageView>(R.id.exam_icon)
        var approved_icon = findViewById<ImageView>(R.id.pay_icon)
        var settings_icon = findViewById<ImageView>(R.id.food_icon)
        var profile_icon = findViewById<ImageView>(R.id.library_icon)

//        var b = findViewById<Button>(R.id.textView6)
//        b.setOnClickListener{
//            print("yes")
//        }
        // Define click listeners for each icon
        apply_icon.setOnClickListener{
            var applicatonIntent  = Intent(this,ApplyLeave::class.java).putExtra("Email","$a")
            startActivity(applicatonIntent)
        }
        pending_icon.setOnClickListener{
            var pending = Intent(applicationContext,pending::class.java).putExtra("Email","$a")
            startActivity(pending)
        }
        rejected_icon.setOnClickListener{
            var applyLeave = Intent(applicationContext,Rejected::class.java).putExtra("Email","$a")
            startActivity(applyLeave)
        }
        approved_icon.setOnClickListener{
            var applyLeave = Intent(applicationContext,Approved::class.java).putExtra("Email","$a")
            startActivity(applyLeave)
        }
    }


}