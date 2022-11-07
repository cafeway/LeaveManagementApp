package com.example.myapplication.Ui.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.GetResponse
import com.example.myapplication.Models.Reg_error
import com.example.myapplication.R
import com.example.myapplication.Ui.employee.EmployeeDash
import com.example.myapplication.adapters.AdminAdapter
import com.example.myapplication.interfaces.AuthenicationInterface
import com.example.myapplication.interfaces.EmployeeInterface
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class pending : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    //    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: AdminAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending2)
        supportActionBar?.hide()

        var recyclerView: RecyclerView = findViewById(R.id.adminRvPending)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


        // Passing User Email as an intent extra
        val bundle: Bundle? = intent.extras
        val a = bundle?.get("Email")

        // Create Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(EmployeeInterface::class.java)

        service.getUserInfo()?.enqueue(object : Callback<GetResponse> {

            override fun onResponse(call: Call<GetResponse>, response: Response<GetResponse>) {
                adapter =
                    response.body()?.application?.let { AdminAdapter(applicationContext, it) }!!

                recyclerView.adapter = adapter
                adapter.setOnItemClickListener(object : AdminAdapter.OnItemClickListener {
                    override fun onItemClicked(positon: Int) {
                        var a = response.body()!!.application[0].EmployeeId
                        var builder = AlertDialog.Builder(this@pending)
                        builder.setTitle("Leave admin actions")
                        builder.setMessage("Use your administrative actions on the application")
                        builder.setIcon(R.drawable.ic_baseline_group_add_24)
                        builder.setPositiveButton("approve"){
                            dialogInterace,which->
                            Toast.makeText(applicationContext,"The leave application has been approved",Toast.LENGTH_SHORT).show()
                            approve(a)
                        }
                        builder.setNegativeButton("reject"){
                                dialogInterace,which->
                            Toast.makeText(applicationContext,"The leave application has been rejected",Toast.LENGTH_SHORT).show()
                            reject(a)
                        }

                        var alertDialog:AlertDialog = builder.create()
                        alertDialog.setCancelable(false)
                        alertDialog.show()
                    }

                })

            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
    fun approve(id: String)
    {
        // Create Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(EmployeeInterface::class.java)

        service.Approve("$id")?.enqueue(object : Callback<GetResponse> {

            override fun onResponse(call: Call<GetResponse>, response: Response<GetResponse>) {
               Log.d("yes","$response")

            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
        }
    fun reject(id: String)
    {
        // Create Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(EmployeeInterface::class.java)

        service.Reject("$id")?.enqueue(object : Callback<GetResponse> {

            override fun onResponse(call: Call<GetResponse>, response: Response<GetResponse>) {
                Log.d("yes","$response")

            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}