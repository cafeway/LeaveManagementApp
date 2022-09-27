package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.myapplication.Models.Reg_error
import com.example.myapplication.interfaces.AuthenicationInterface
import com.example.myapplication.databinding.ActivitySignInBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class sign_in : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Login button
        val loginButton = findViewById<Button>(R.id.button)

        // click listener
        loginButton.setOnClickListener() {
            login()
        }
    }

    fun login(){


        // get texts fields via id
        val EmailField = findViewById<EditText>(R.id.Email)
        val PasswordField = findViewById<EditText>(R.id.Password)

        // Create a login model with text from EditText
        val loginModel = Model(EmailField.text.toString(),PasswordField.text.toString())

        // Create Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(AuthenicationInterface::class.java)

        // Launch an asynchrounous operation using co-routines
        CoroutineScope(Dispatchers.IO).launch {
            // Perform The post Request and get a response
            val response = service.login(loginModel)
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
                        "Email"->EmailField.setError(bodyGson.Error[0])
                        "Password"->PasswordField.setError(bodyGson.Error[0])
                        "Success"->{
                            Toast.makeText(applicationContext, "Login was successful",Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            val intent2 = Intent(applicationContext,EmployeeDash::class.java).putExtra("Email",EmailField.text.toString())
                            if (bodyGson.Error[0] == "employee")
                            {
                                startActivity(intent2)
                            } else {
                                startActivity(intent)
                            }

                        }
                        "LoginError"->{
                            Toast.makeText(applicationContext, "${bodyGson.Error[0]}",Toast.LENGTH_LONG).show()
                            EmailField.setError(bodyGson.Error[0])
                            PasswordField.setError(bodyGson.Error[0])

                        }
                    }
                }else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }

    }
}
