package com.example.myapplication.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetResponse(
    @SerialName("application")
    val application: ArrayList<Application>
)
