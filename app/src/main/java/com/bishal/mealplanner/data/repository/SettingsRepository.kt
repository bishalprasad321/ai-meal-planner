package com.bishal.mealplanner.data.repository

import com.bishal.mealplanner.data.local.SettingsDataStore

class SettingsRepository(private val settingsDataStore: SettingsDataStore) {
    // Expose flows
    val dietType = settingsDataStore.dietType
    val calorieGoal = settingsDataStore.calorieGoal

    // Save methods
    suspend fun saveDietType(diet: String) {
        settingsDataStore.saveDietType(diet)
    }

    suspend fun saveCalorieGoal(goal: Int) {
        settingsDataStore.saveCalorieGoal(goal)
    }
}