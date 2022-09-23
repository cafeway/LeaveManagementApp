package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import com.example.myapplication.Employee
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        val schools_spinner = findViewById<Spinner>(R.id.schoolsSpinner)
        val county_spinner = findViewById<Spinner>(R.id.coursesSpinner)
        val gender = findViewById<Spinner>(R.id.genderSpinner)
        ArrayAdapter.createFromResource(
            this,R.array.schools,
            android.R.layout.simple_spinner_item
        ).also {
                adapter-> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            schools_spinner.adapter = adapter
        }

        // creating a spinner adapaters show a list of county as options
        ArrayAdapter.createFromResource(
            this,R.array.County,
            android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                county_spinner.adapter = adapter
            }


        // creating a spinner adapaters show a list of genders as options
        ArrayAdapter.createFromResource(
            this,R.array.gender,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            gender.adapter = adapter
        }

        val submit = findViewById<Button>(R.id.register)
        submit.setOnClickListener(){
            Register()
        }

    }
    fun Register () {
        //spinnners for error message rendering
        val EmployeeId_ = findViewById<EditText>(R.id.RegNo)
        val FirstName_ = findViewById<EditText>(R.id.FirstName)
        val SecondName_ = findViewById<EditText>(R.id.SecondName)
        val IdNo_ = findViewById<EditText>(R.id.Id)
        val PhoneNumber_ = findViewById<EditText>(R.id.PhoneNumber)
        val Nssf_ = findViewById<EditText>(R.id.Nssf)
        val Email_ = findViewById<EditText>(R.id.ThirdName)
        val Password_ = findViewById<EditText>(R.id.Password)

//            val _ = findViewById<EditText>(R.id.MothersName)
//            val FathersPhone_ = findViewById<EditText>(R.id.FathersPhone)
//            val MothersPhone_ = findViewById<EditText>(R.id.MothersPhone)


//        val id = parseInt(IdNo)

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(EmployeeInterface::class.java)


        val EmployeeId = findViewById<EditText>(R.id.RegNo)
        val FirstName = findViewById<EditText>(R.id.FirstName)
        val SecondName = findViewById<EditText>(R.id.SecondName)
        val IdNo = findViewById<EditText>(R.id.Id)
        val PhoneNumber = findViewById<EditText>(R.id.PhoneNumber)
        val Nssf = findViewById<EditText>(R.id.Nssf)
        val Email = findViewById<EditText>(R.id.ThirdName)
        val Password = findViewById<EditText>(R.id.Password)
        val Department = findViewById<Spinner>(R.id.schoolsSpinner)
        val Position = findViewById<Spinner>(R.id.coursesSpinner)
        val Gender = findViewById<Spinner>(R.id.genderSpinner)
        // Create JSON using JSONObject

        val employeeObject =  Employee(
            EmployeeId.text.toString(),
            FirstName.text.toString(),
            SecondName.text.toString(),
            IdNo.text.toString(),
            PhoneNumber.text.toString(),
            Position.selectedItem.toString(),
            Department.selectedItem.toString(),
                    Email.text.toString(),
            Password.text.toString(),
            Nssf.text.toString(),

            Gender.selectedItem.toString(),
        )

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.registerEmployee(employeeObject)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("success","the api call was successfull")
////                    Log.d("success","the api call was successfull")
                    val items =  response.body()?.string()
                    Log.d("yes","$items")
//                    items['Regbo'.toInt()]
//                    val a  = items?.length




                    val gson = Gson()
                    val b = gson.fromJson(items,Reg_error::class.java)
                    Log.d("aa","${b.Error}")
                    var toast= Toast.makeText(applicationContext,"Employee account has been created successfully",Toast.LENGTH_LONG)


                    when(b.Field)
                    {
                        "EmployeeId"->EmployeeId_.setError(b.Error[0])
                        "FirstName"->FirstName_.setError(b.Error[0])
                        "LastName"->SecondName_.setError(b.Error[0])
                        "Email"->Email_.setError(b.Error[0])
                        "Password"->Password_.setError(b.Error[0])
                        "IdNo"->IdNo_.setError(b.Error[0])
//                            "PhoneNumber"->PhoneNumber.setError(b.Error[0])
//                            "MothersName"->MothersName.setError(b.Error[0])
//                            "FathersPhone"->FathersPhone.setError(b.Error[0])
                        "Nssf"->Nssf_.setError(b.Error[0])
                        "PhoneNumber"->PhoneNumber_.setError(b.Error[0])
                        "Success"->{
                            toast.show()
                            Log.d("dd","success")
                        }
                        else-> {
                            Log.d("yes","${response.code()}")
                        }

                    }

                } else {

                    Log.e("RETROFIT_ERROR", response.body().toString())

                }
            }
        }

    }

}

class SpinnnerActivity: Activity(), AdapterView.OnItemSelectedListener{
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
        parent?.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}