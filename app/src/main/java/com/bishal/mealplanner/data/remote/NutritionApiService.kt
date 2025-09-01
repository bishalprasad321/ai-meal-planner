package com.bishal.mealplanner.data.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NutritionApiService {
    @GET("nutrition")
    suspend fun getNutritionInfo(
        @Query("query") query: String,
        @Header("X-Api-Key") apiKey: String
    ): List<NutritionResponse>
}