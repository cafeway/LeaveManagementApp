package com.example.myapplication.interfaces

import com.example.myapplication.Models.Application
import com.example.myapplication.Models.Employee
import com.example.myapplication.Models.GetResponse
import com.example.myapplication.Models.messagePayload
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EmployeeInterface {
    @POST("https://lmske.herokuapp.com/api/employee")
    suspend fun registerEmployee(@Body employee: Employee): Response<ResponseBody>

    @POST("https://lmske.herokuapp.com/api/application")
    suspend fun apply(@Body application: Application): Response<ResponseBody>


    @GET("https://lmske.herokuapp.com/api/application/show")
    fun getUserInfo():Call<GetResponse>

    @GET("https://lmske.herokuapp.com/api/approvedAdmin")
    fun getApproved():Call<GetResponse>
    @GET("https://lmske.herokuapp.com/api/rejectedAdmin")
    fun getRejected():Call<GetResponse>

//    @GET("https://10.0.2.2:8000/api/")

    // gets users pending applications using email and status
    @GET("https://lmske.herokuapp.com/api/application")
    fun getUserInfoByUser(@Query("Id") Id:String,@Query("status") status:String):Call<GetResponse>

    @POST("https://lmske.herokuapp.com/api/rejected")
    fun Approve(@Query ("id") id:String): Call<GetResponse>

    @POST("https://lmske.herokuapp.com/api/approved")
    fun Reject(@Query ("id") id:String): Call<GetResponse>

    @POST("https://lmske.herokuapp.com/api/sendSms")
    fun sendMessage(@Body message: messagePayload): Response<ResponseBody>

}