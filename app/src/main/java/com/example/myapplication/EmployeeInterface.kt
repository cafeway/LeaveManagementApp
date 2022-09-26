package com.example.myapplication

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EmployeeInterface {
    @POST("  https://lmske.herokuapp.com/api/employee")
    suspend fun registerEmployee(@Body employee: Employee): Response<ResponseBody>

    @POST("https://lmske.herokuapp.com/api/application")
    suspend fun apply(@Body application: Application): Response<ResponseBody>


    @GET("https://lmske.herokuapp.com/api/application/show")
    fun getUserInfo():Call<MutableList<Application>>
}