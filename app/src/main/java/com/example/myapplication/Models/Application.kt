package com.example.myapplication.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Application(
    @SerialName("EmployeeId")
    val EmployeeId: String,

    @SerialName("LeaveType")
    val LeaveType:String,

    @SerialName("Department")
    val Department: String,

    @SerialName("StartDate")
    val StartDate: String,

    @SerialName("Duration")
    var Duration: Int,

    @SerialName("Remarks")
     var Remarks: String,

    @SerialName("Email")
    var Email: String,
)
