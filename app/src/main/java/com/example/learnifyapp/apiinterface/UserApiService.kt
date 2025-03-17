package com.example.learnifyapp.apiinterface

import com.example.learnifyapp.data.UserProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApiService {
    @GET("/auth/me")
    suspend fun getUserProfile(@Header("Authorization") token: String): Response<UserProfileResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val grade: String,
    val instituteName: String
)

data class RegisterResponse(
    val message: String,
    val token: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val message: String,
    val token: String
)