package com.example.myapplication.Ui.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Login
import com.example.myapplication.Models.GetResponse
import com.example.myapplication.R
import com.example.myapplication.adapters.ApprovedAdapter
import com.example.myapplication.adapters.RejectedAdapter
import com.example.myapplication.interfaces.EmployeeInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Rejected : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    //    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: RejectedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rejected2)
        supportActionBar?.hide()
        // getting the recycler view
        var recyclerView: RecyclerView = findViewById(R.id.adminRvrejected)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


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

        service.getRejected()?.enqueue(object: Callback<GetResponse> {

            override fun onResponse(call: Call<GetResponse>, response: Response<GetResponse>) {
                adapter = response.body()?.application?.let { RejectedAdapter(this@Rejected, it) }!!

                recyclerView.adapter = adapter
                adapter.setOnItemClickListener(object: RejectedAdapter.OnItemClickListener{
                    override fun onItemClicked(positon: Int) {
                        var a = response.body()!!.application[0].EmployeeId
                        Log.d("sdsd","$a")
                    }

                })

            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

        val fab = findViewById<FloatingActionButton>(R.id.button)

//        fab.setOnClickListener() {
//            val intent = Intent(this, Login::class.java).apply {
//
//            }
//            startActivity(intent)
//        }
    }
}