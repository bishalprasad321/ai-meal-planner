package com.bishal.mealplanner.data.repository

import com.bishal.mealplanner.data.local.MealDao
import com.bishal.mealplanner.data.local.MealEntity
import com.bishal.mealplanner.data.remote.NutritionResponse
import com.bishal.mealplanner.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class MealRepository(private val dao: MealDao) {
    fun getAllMeals(): Flow<List<MealEntity>> = dao.getAllMeals()

    suspend fun insertMeal(meal: MealEntity)  = dao.insertMeal(meal)

    suspend fun clearMeals() = dao.clearMeals()

    suspend fun fetchNutrition(query: String, apiKey: String): List<NutritionResponse> {
        return RetrofitInstance.api.getNutritionInfo(query = query, apiKey = apiKey)
    }
}