package com.example.myapplication.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reg_error(
    @SerialName("error")
    var Field: String,
    var Error: List<String>
)