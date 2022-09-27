package com.example.myapplication

import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Models.Application
import com.example.myapplication.Models.Reg_error
import com.example.myapplication.interfaces.EmployeeInterface
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplyLeave : AppCompatActivity(){
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)




        val schools_spinner = findViewById<Spinner>(R.id.schoolsSpinner)
        val duration = findViewById<Spinner>(R.id.schoolsSpinner2)

        val calendar = Calendar.getInstance()
        val date = calendar.get(Calendar.DATE)


        // set min day for picker
        val picker = findViewById<DatePicker>(R.id.DatePicker)
        picker.minDate = System.currentTimeMillis()-1000
        Log.d("date","")
        ArrayAdapter.createFromResource(
            this,R.array.Leaves,
            android.R.layout.simple_spinner_item
        ).also {
                adapter-> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            schools_spinner.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,R.array.Duration,
            android.R.layout.simple_spinner_item
        ).also {
                adapter-> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            duration.adapter = adapter
        }

//        // inflate layout with a button Click
        val button = findViewById<Button>(R.id.apply)
        button.setOnClickListener{
            apply()
        }
    }

    fun apply () {
        val bundle: Bundle? = intent.extras
        val a = bundle?.get("Email")

        //get date from user
        val picker = findViewById<DatePicker>(R.id.DatePicker)
        val day = picker.dayOfMonth.toString()
        var month = picker.month + 1
        val newMonth = month
        val year = picker.year.toString()

        val date = "$day-$newMonth-$year"

        // get pickers by reference
        val schools_spinner = findViewById<Spinner>(R.id.schoolsSpinner)
        val duration = findViewById<Spinner>(R.id.schoolsSpinner2)

        // create a model using the data
        val applicationModel = Application(date,schools_spinner.selectedItem.toString(),duration.selectedItem.toString(),"$a")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()


        // Create Service
        val service = retrofit.create(EmployeeInterface::class.java)

        // Launch an asynchrounous operation using co-routines
        CoroutineScope(Dispatchers.IO).launch {
            // Perform The post Request and get a response
            val response = service.apply(applicationModel)
            withContext(Dispatchers.Main){
                // Test if the respsonce was successfull
                if (response.isSuccessful){
                    Log.d("Success","Request was success full")
                    val body = response.body()?.string()
                    Log.d("Success","$body")
//                     convert the json response obtained to gson and cast to a model
                    val gson = Gson()
                    val bodyGson = gson.fromJson(body, Reg_error::class.java)

                    Log.d("bodyJson", bodyGson.Field.toString())

//                     loop the body Gson for errors or success messages
                    val errorField = bodyGson.Field

                    when(errorField){
                        "Success"->{
                            Toast.makeText(applicationContext, "Your Leave Application was successful",Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext, EmployeeDash::class.java)
                            startActivity(intent)
                        }
                        "Application"->{
                            Toast.makeText(applicationContext, "${bodyGson.Error[0]}",Toast.LENGTH_LONG).show()


                        }
                    }
                }else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }


    }


}
