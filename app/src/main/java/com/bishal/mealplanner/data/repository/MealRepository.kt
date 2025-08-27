package com.bishal.mealplanner.data.repository

import com.bishal.mealplanner.data.local.MealDao
import com.bishal.mealplanner.data.local.MealEntity
import kotlinx.coroutines.flow.Flow

class MealRepository(private val dao: MealDao) {
    fun getAllMeals(): Flow<List<MealEntity>> = dao.getAllMeals()

    suspend fun insertMeal(meal: MealEntity)  = dao.insertMeal(meal)

    suspend fun clearMeals() = dao.clearMeals()
}