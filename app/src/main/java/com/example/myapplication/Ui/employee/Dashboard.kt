package com.example.myapplication.Ui.employee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
//
//         get all buttons by view
        var apply_button = findViewById<ImageView>(R.id.apply_icon)
        var pending_button = findViewById<ImageView>(R.id.accomodation_icon)
        var rejected_button = findViewById<ImageView>(R.id.exam_icon)
        var approved_button = findViewById<ImageView>(R.id.pay_icon)
        var profile_button = findViewById<ImageView>(R.id.food_icon)
        var settings_button = findViewById<ImageView>(R.id.library_icon)

        //set click listeners for each to navigate to new page
        apply_button.setOnClickListener{
            var applyLeave = Intent(applicationContext,pending::class.java)
            startActivity(applyLeave)
        }
        pending_button.setOnClickListener{
            var applyLeave = Intent(applicationContext,pending::class.java)
            startActivity(applyLeave)
        }
        apply_button.setOnClickListener{
            var applyLeave = Intent(applicationContext,pending::class.java)
            startActivity(applyLeave)
        }
        apply_button.setOnClickListener{
            var applyLeave = Intent(applicationContext,pending::class.java)
            startActivity(applyLeave)
        }

        pending_button.setOnClickListener{
            var pending = Intent(applicationContext,pending::class.java)
            startActivity(pending)
        }

    }
}