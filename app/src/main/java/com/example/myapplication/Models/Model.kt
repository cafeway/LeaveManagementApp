package com.example.myapplication.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Model(
    @SerialName("Email")
    var Email: String,

    @SerialName("Password")
    var Password: String
)
