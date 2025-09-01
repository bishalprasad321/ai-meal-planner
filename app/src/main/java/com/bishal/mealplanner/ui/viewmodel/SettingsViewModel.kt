package com.bishal.mealplanner.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bishal.mealplanner.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val _dietType = MutableStateFlow("")
    val dietType: StateFlow<String> = _dietType.asStateFlow()

    private val _calorieGoal = MutableStateFlow(2000) // default calorie goal
    val calorieGoal: StateFlow<Int> = _calorieGoal.asStateFlow()

    init {
        // Collect flows from repository and update state
        viewModelScope.launch {
            settingsRepository.dietType.collectLatest { type ->
                _dietType.value = type
            }
        }
        viewModelScope.launch {
            settingsRepository.calorieGoal.collectLatest { goal ->
                _calorieGoal.value = goal
            }
        }
    }
    fun saveDietType(diet: String) {
        viewModelScope.launch {
            settingsRepository.saveDietType(diet)
        }
    }

    fun saveCalorieGoal(goal: Int) {
        viewModelScope.launch {
            settingsRepository.saveCalorieGoal(goal)
        }
    }

    fun saveSettings(diet: String, goal: Int) {
        viewModelScope.launch {
            settingsRepository.saveDietType(diet)
            settingsRepository.saveCalorieGoal(goal)
        }
    }
}