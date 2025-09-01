package com.bishal.mealplanner.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension property to access DataStore
val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_prefs")

class SettingsDataStore(private val context: Context) {
    companion object {
        private val DIET_TYPE_KEY = stringPreferencesKey("diet_type")
        private val CALORIE_GOAL_KEY = intPreferencesKey("calorie_goal")
    }

    // Read diet type
    val dietType: Flow<String> = context.settingsDataStore.data
        .map { prefs ->
            prefs[DIET_TYPE_KEY] ?: "Balanced" // default diet
        }

    // Read calorie goal
    val calorieGoal: Flow<Int> = context.settingsDataStore.data
        .map { prefs ->
            prefs[CALORIE_GOAL_KEY] ?: 2000 // default kcal
        }

    // Save diet type
    suspend fun saveDietType(diet: String) {
        context.settingsDataStore.edit { prefs ->
            prefs[DIET_TYPE_KEY] = diet
        }
    }

    // Save calorie goal
    suspend fun saveCalorieGoal(goal: Int) {
        context.settingsDataStore.edit { prefs ->
            prefs[CALORIE_GOAL_KEY] = goal
        }
    }
}