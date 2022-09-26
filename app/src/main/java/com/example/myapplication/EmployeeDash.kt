package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.JsonToken
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityEmployeeDashBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class EmployeeDash : AppCompatActivity() {

    lateinit var adapter: ApplicationAdapter
    lateinit var applicationRV: RecyclerView
    lateinit var applicationList: ArrayList<Application>


    override fun onCreate(savedInstanceState: Bundle?) {



        applicationList = ArrayList()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_employee_dash)



        // Passing User Email as an intent extra
        val bundle: Bundle? = intent.extras
        val a = bundle?.get("Email")

        getApplications()


        // inflate layout with a button Click
        val button = findViewById<FloatingActionButton>(R.id.button)
        button.setOnClickListener{
            val intent2 = Intent(applicationContext,ApplyLeave::class.java).putExtra("Email","Email1@gmail.com")
            startActivity(intent2)
        }

    }

    private fun getApplications() {
        // Create Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(EmployeeInterface::class.java)


        // call the api
        service.getUserInfo()?.enqueue(object : Callback<MutableList<Application>>{
            override fun onResponse(
                call: Call<MutableList<Application>>,
                response: Response<MutableList<Application>>
            ) {


            }

            override fun onFailure(call: Call<MutableList<Application>>, t: Throwable) {
                Log.d("error","adapterhas error")
            }

        })
    }




}