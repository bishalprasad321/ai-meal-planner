package com.bishal.mealplanner.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_history")
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUri: String?,
    val foodName: String,
    val calories: Int,
    val timestamp: Long
)
