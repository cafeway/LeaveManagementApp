package com.example.myapplication.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Application(
    @SerialName("Date")
    val Date: String,

    @SerialName("Type")
    val Type:String,

    @SerialName("Duration")
    val Duration: String,

    @SerialName("UserId")
    val UserId: String,

    @SerialName("Status")
    var Status: String = "pending"
)
