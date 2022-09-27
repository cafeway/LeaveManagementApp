package com.example.myapplication.interfaces



import com.example.myapplication.Model
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenicationInterface {
    @POST("https://lmske.herokuapp.com/api/login")
    suspend fun login (@Body model: Model): Response<ResponseBody>
}