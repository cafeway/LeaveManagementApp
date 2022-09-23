package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ViewApplications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_applications)
        val bar = supportActionBar
        bar?.hide()
    }
}