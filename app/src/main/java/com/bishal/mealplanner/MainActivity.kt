package com.bishal.mealplanner

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.bishal.mealplanner.data.local.MealDatabase
import com.bishal.mealplanner.data.repository.MealRepository
import com.bishal.mealplanner.ui.theme.MealPlannerTheme
import com.bishal.mealplanner.ui.viewmodel.MealHistoryViewModel
import com.bishal.mealplanner.ui.viewmodel.MealHistoryViewModelFactory
import com.bishal.mealplanner.utils.SetupNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Room DB + Repository
        val database = MealDatabase.getDatabase(applicationContext)
        val repository = MealRepository(database.mealDao())

        // Provide ViewModel with factory
        val viewModel: MealHistoryViewModel by viewModels {
            MealHistoryViewModelFactory(repository)
        }

        enableEdgeToEdge()
        setContent {
            MealPlannerTheme {
                MealPlannerApp()
            }
        }
    }
}

@Composable
fun MealPlannerApp() {
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
}

@Preview(showBackground = true)
@Composable
private fun MealPlannerAppPreview() {
    MealPlannerApp()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MealPlannerAppDarkPreview() {
    MealPlannerApp()
}