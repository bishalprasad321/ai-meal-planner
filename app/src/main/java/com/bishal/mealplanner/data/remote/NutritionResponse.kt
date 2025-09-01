package com.bishal.mealplanner.data.remote

data class NutritionResponse(
    val name: String,
    val calories: Double,
    val servingSize: Double,
    val fatTotal: Double,
    val fatSaturated: Double,
    val protein: Double,
    val sodium: Double,
    val potassium: Double,
    val cholesterol: Double,
    val carbohydratesTotal: Double,
    val fiber: Double,
    val sugar: Double
)
