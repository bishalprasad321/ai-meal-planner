package com.bishal.mealplanner.ui.settings

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bishal.mealplanner.data.local.SettingsDataStore
import com.bishal.mealplanner.data.repository.SettingsRepository
import com.bishal.mealplanner.ui.viewmodel.SettingsViewModel
import com.bishal.mealplanner.ui.viewmodel.SettingsViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val settingsDataStore = SettingsDataStore(context)
    val repository = SettingsRepository(settingsDataStore)
    val factory = SettingsViewModelFactory(repository)
    val viewModel: SettingsViewModel = viewModel(factory = factory)

    val scope = rememberCoroutineScope()

    val dietType by viewModel.dietType.collectAsState()
    val dailyCalories by viewModel.calorieGoal.collectAsState()

    var selectedDietType by remember { mutableStateOf(dietType) }
    var caloriesInput by remember { mutableStateOf(dailyCalories.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Diet type dropdown
        Text(
            text = "Diet Type",
            style = MaterialTheme.typography.titleMedium
        )
        var expanded by remember { mutableStateOf(false) }

        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(text = selectedDietType.ifEmpty { "Choos Diet Type" })
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                val dietOptions = listOf("Balanced", "High Protein", "Low Carb", "Vegan", "Keto")
                dietOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selectedDietType = option
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Daily Calorie Goal Input
        Text(
            text = "Daily Calorie Goal",
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedTextField(
            value = caloriesInput,
            onValueChange = { caloriesInput = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Save Button
        Button(
            onClick = {
                scope.launch {
                    viewModel.saveSettings(
                        diet = selectedDietType,
                        goal = caloriesInput.toIntOrNull() ?: 2000
                    )
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SettingsScreenPreviewDark() {
   SettingsScreen()
}
