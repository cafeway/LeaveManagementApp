package com.example.myapplication.Ui.employee

import android.content.Intent
import android.hardware.biometrics.BiometricManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager.Authenticators.*
import androidx.core.content.ContextCompat
import com.example.myapplication.Models.Application
import com.example.myapplication.Models.Reg_error
import com.example.myapplication.R
import com.example.myapplication.interfaces.EmployeeInterface
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.Executor
import com.google.firebase.iid.FirebaseInstanceIdReceiver
class ApplyLeave : AppCompatActivity(){
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
    private lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo
    private var Email:String = ""
    private val calender = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        val schools_spinner = findViewById<Spinner>(R.id.departmentsSpinner)
        val departments_spinner = findViewById<Spinner>(R.id.dep)

        val datePicker = findViewById<DatePicker>(R.id.datePicker)

        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)

        // set the minimum date for the datepicker
        datePicker.minDate =  calender.timeInMillis


        val bundle: Bundle? = intent.extras
        val a = bundle?.get("Email")
        var mail = a.toString()
        ArrayAdapter.createFromResource(
            this, R.array.schools,
            android.R.layout.simple_spinner_item
        ).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            departments_spinner.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this, R.array.Leaves,
            android.R.layout.simple_spinner_item
        ).also {
                adapter-> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            schools_spinner.adapter = adapter
        }


//        // inflate layout with a button Click
        val button = findViewById<Button>(R.id.apply)
        button.setOnClickListener{
//            checkIfDeviceHasBiometrics()
            applyLeave(mail)
        }

        // work with executor
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = androidx.biometric.BiometricPrompt(this@ApplyLeave,executor,biometricCallback)
        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("User Verification")
            .setSubtitle("Place your finger on the scanner")
            .setNegativeButtonText("Cancel")
            .build()
    }

    val biometricCallback = object : androidx.biometric.BiometricPrompt.AuthenticationCallback(){
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            Toast.makeText(this@ApplyLeave,"Authentication error",Toast.LENGTH_LONG).show()
        }

        override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            Toast.makeText(this@ApplyLeave,"Your identity has been verified",Toast.LENGTH_LONG).show()
            applyLeave("ddd")
        }

    }

    private fun applyLeave(email: String) {

        // get instances of the input fields
        val EmployeeId = findViewById<EditText>(R.id.EmpId)
        val leaveType = findViewById<Spinner>(R.id.departmentsSpinner)
        val department = findViewById<Spinner>(R.id.dep)
        val datePicker = findViewById<DatePicker>(R.id.datePicker)

        // getting the date and converting it to a string
        var month = datePicker.month.toString()
        var dayOfWeek = datePicker.dayOfMonth.toString()
        var year = datePicker.year.toString()
        var time = calender.time.toString()
        val fullDate = "$year-$month-$dayOfWeek-$time"

        //  get values from the input field
        val id = EmployeeId.text.toString()
        val type= leaveType.selectedItem.toString()
        val workers_department = department.selectedItem.toString()
        val remarks = "applying to leave"
        val duration = getDuration(leaveType.selectedItem.toString())

        // create a model of application to be passed to the retrofit post request
        val application = Application(id,type,workers_department,fullDate,duration,remarks,email)

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(EmployeeInterface::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get a response back
            val response = service.apply(application)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful){
                    val items = response.body()?.string()

                    // initiate a object to json converter
                    val gson = Gson()
                    // convert and map the response to the Reg_error model
                    val converted_data = gson.fromJson(items,Reg_error::class.java)
                    when(converted_data.Field){
                        "Success"-> Toast.makeText(this@ApplyLeave,"Your application was successful",Toast.LENGTH_LONG).show()
                        "Application"-> Toast.makeText(this@ApplyLeave,"Your have a pending leave awaiting approval",Toast.LENGTH_LONG).show()
                        "Id"-> Toast.makeText(this@ApplyLeave,"The employee id entered does not exist",Toast.LENGTH_LONG).show()
                    }
                }else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }



    }

    private fun getDuration(leaveType: String): Int {
        var duration:Int = 0
        when(leaveType){
            "Maternal Leave" -> duration =24
            "Sick Leave" ->  duration =8
            "Weekly Leave"->  duration =1
            "Annual Leave"-> duration = 48
            "Emergency Leave" ->  duration =2
        }
        return duration
    }

    // checks if a device has biometrics
    fun checkIfDeviceHasBiometrics (){
        val biometricManager = androidx.biometric.BiometricManager.from(this)
        when(biometricManager.canAuthenticate(BIOMETRIC_STRONG or BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS-> {
                biometricPrompt.authenticate(promptInfo)
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED->{
                val intent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED, BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                }
            }
        }
    }

}
