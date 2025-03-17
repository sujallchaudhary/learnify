package com.example.learnifyapp.apiinterface

import com.example.learnifyapp.data.TriviaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApiService {
    @GET("api.php")
    suspend fun getTriviaQuestions(
        @Query("amount") amount: Int
    ): Response<TriviaResponse>
}