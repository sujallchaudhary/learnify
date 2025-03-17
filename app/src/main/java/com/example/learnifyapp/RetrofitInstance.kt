package com.example.learnifyapp

import com.example.learnifyapp.apiinterface.QuizApiService
import com.example.learnifyapp.apiinterface.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val USER_BASE_URL = "https://learnify.sujal.info/api/v1/"
    private const val QUIZ_BASE_URL = "https://opentdb.com/"

    val userApiService: UserApiService by lazy {
        Retrofit.Builder()
            .baseUrl(USER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }

    val quizApiService: QuizApiService by lazy {
        Retrofit.Builder()
            .baseUrl(QUIZ_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApiService::class.java)
    }
}