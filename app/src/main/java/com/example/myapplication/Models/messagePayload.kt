package com.example.myapplication.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class messagePayload(
    @SerialName("id")
    val id: String,
    @SerialName("message")
    val message: String
)
