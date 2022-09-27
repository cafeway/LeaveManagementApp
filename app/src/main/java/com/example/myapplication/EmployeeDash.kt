package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.Application
import com.example.myapplication.Models.GetResponse
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
        setContentView(R.layout.content_employee_dash)

        // getting the recycler view
        var recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        var adapter: ApplicationAdapter
        // Passing User Email as an intent extra
        val bundle: Bundle? = intent.extras
        val a = bundle?.get("Email")

        // Create Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(EmployeeInterface::class.java)

        service.getUserInfoByUser("$a")?.enqueue(object: Callback<GetResponse>{
            override fun onResponse(call: Call<GetResponse>, response: Response<GetResponse>) {
                recyclerView.adapter =
                    response.body()?.let { ApplicationAdapter(this@EmployeeDash, it.application) }
            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

        // inflate layout with a button Click
        val button = findViewById<FloatingActionButton>(R.id.button)
        button.setOnClickListener{
            val intent2 = Intent(applicationContext,ApplyLeave::class.java).putExtra("Email","Email1@gmail.com")
            startActivity(intent2)

        }

    }










}