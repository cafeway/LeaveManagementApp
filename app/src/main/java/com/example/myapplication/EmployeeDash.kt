package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.myapplication.databinding.ActivityEmployeeDashBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EmployeeDash : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_employee_dash)

        val bundle: Bundle? = intent.extras
        val a = bundle?.get("Email")

        Log.d("Email","$a")


        // inflate layout with a button Click
        val button = findViewById<FloatingActionButton>(R.id.button)
        button.setOnClickListener{
            val intent2 = Intent(applicationContext,ApplyLeave::class.java).putExtra("Email","$a")
            startActivity(intent2)
        }
    }


}