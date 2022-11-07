package com.example.myapplication.Ui.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.myapplication.Login
import com.example.myapplication.R
import com.example.myapplication.Ui.employee.pending

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard2)
        supportActionBar?.hide()
        var apply_button = findViewById<ImageView>(R.id.addEmp)
        var pending_button = findViewById<ImageView>(R.id.pend)
        var rejected_button = findViewById<ImageView>(R.id.rej)
        var approved_button = findViewById<ImageView>(R.id.app)
//        var profile_button = findViewById<ImageView>(R.id.food_icon)
//        var settings_button = findViewById<ImageView>(R.id.library_icon)

        //set click listeners for each to navigate to new page
        apply_button.setOnClickListener{
            var applyLeave = Intent(applicationContext, Login::class.java)
            startActivity(applyLeave)
        }
        pending_button.setOnClickListener{
            var applyLeave = Intent(applicationContext, com.example.myapplication.Ui.admin.pending::class.java)
            startActivity(applyLeave)
        }
        rejected_button.setOnClickListener{
            var applyLeave = Intent(applicationContext, Rejected::class.java)
            startActivity(applyLeave)
        }
        approved_button.setOnClickListener{
            var applyLeave = Intent(applicationContext, Approved::class.java)
            startActivity(applyLeave)
        }

//        pending_button.setOnClickListener{
//            var pending = Intent(applicationContext, pending::class.java)
//            startActivity(pending)
//        }

    }
}