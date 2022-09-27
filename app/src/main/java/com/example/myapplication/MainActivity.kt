package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.GetResponse
import com.example.myapplication.adapters.AdminAdapter
import com.example.myapplication.adapters.ApplicationAdapter
import com.example.myapplication.interfaces.EmployeeInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var binding: ActivityMainBinding
      lateinit var adapter: AdminAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the recycler view
        var recyclerView: RecyclerView = findViewById(R.id.adminRv)
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

        service.getUserInfo()?.enqueue(object: Callback<GetResponse> {

            override fun onResponse(call: Call<GetResponse>, response: Response<GetResponse>) {
                adapter = response.body()?.application?.let { AdminAdapter(this@MainActivity, it) }!!

                recyclerView.adapter = adapter
                adapter.setOnItemClickListener(object: AdminAdapter.OnItemClickListener{
                    override fun onItemClicked(positon: Int) {
                       Log.d("sdsd","dsds")
                    }

                })
               
            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

        val fab = findViewById<FloatingActionButton>(R.id.button)

        fab.setOnClickListener() {
            val intent = Intent(this, Login::class.java).apply {

            }
            startActivity(intent)
        }
    }
}