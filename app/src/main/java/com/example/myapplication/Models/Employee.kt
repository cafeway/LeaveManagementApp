package com.example.myapplication.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    @SerialName("EmployeeId")
    var EmployeeId: String,

    @SerialName("FirstName")
    var FirstName: String,

    @SerialName("LastName")
    var LastName: String,


    @SerialName("IdNo")
    var IdNo: String,

    @SerialName("PhoneNumber")
    var PhoneNumber: String,

    @SerialName("Position")
    var Position: String,

    @SerialName("Department")
    var Department: String,

    @SerialName("Email")
    var Email: String,

    @SerialName("Password")
    var Password: String,

    @SerialName("NssF")
    var NssF: String,



    @SerialName("Gender")
    var Gender: String,
)
