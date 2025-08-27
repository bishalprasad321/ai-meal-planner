package com.bishal.mealplanner.ui.history

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bishal.mealplanner.data.local.MealDatabase
import com.bishal.mealplanner.data.repository.MealRepository
import com.bishal.mealplanner.ui.components.MealHistoryItem
import com.bishal.mealplanner.ui.viewmodel.MealHistoryViewModel
import com.bishal.mealplanner.ui.viewmodel.MealHistoryViewModelFactory

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: MealHistoryViewModel = viewModel(
        factory = MealHistoryViewModelFactory(
            MealRepository(
                MealDatabase.getDatabase(LocalContext.current).mealDao()
            )
        )
    )
) {
    val mealHistory by viewModel.mealHistory.collectAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Meal History",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (mealHistory.isEmpty()) {
            Text(
                text = "No meal history available.",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(mealHistory) { meal ->
                    MealHistoryItem(meal)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryScreenPreview() {
    HistoryScreen()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HistoryScreenPreviewDark() {
    HistoryScreen()
}