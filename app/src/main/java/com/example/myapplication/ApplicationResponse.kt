package com.example.myapplication

import com.example.myapplication.Models.Application
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApplicationResponse(
    @SerialName("applications")
    val applications: List<Application>
)
