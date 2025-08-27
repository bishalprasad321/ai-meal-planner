package com.bishal.mealplanner.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bishal.mealplanner.data.local.MealEntity
import com.bishal.mealplanner.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MealHistoryViewModel(
    private val repository : MealRepository
) : ViewModel() {
    private val _mealHistory = MutableStateFlow<List<MealEntity>>(emptyList())
    val mealHistory: StateFlow<List<MealEntity>> = _mealHistory.asStateFlow()

    init {
        observeMealHistory()
    }

    private fun observeMealHistory() {
        viewModelScope.launch {
            repository.getAllMeals().collect { meals ->
                _mealHistory.value = meals
            }
        }
    }

    fun addMeal(meal: MealEntity) {
        viewModelScope.launch {
            repository.insertMeal(meal)
        }
    }

    fun clearAllMeals() {
        viewModelScope.launch {
            repository.clearMeals()
        }
    }
}