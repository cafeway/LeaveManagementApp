package com.example.myapplication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.ArrayList

@Serializable
data class ApplicationResponse(
    @SerialName("applications")
    val applications: List<Application>
)
