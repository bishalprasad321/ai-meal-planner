package com.bishal.mealplanner.ui.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bishal.mealplanner.data.local.OnBoardingManager
import com.bishal.mealplanner.utils.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val pages = listOf(
        "Snap a meal -> AI detects food",
        "Get instant calories & nutrition info",
        "Track daily goals with AI suggestions"
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = pages[page], style = MaterialTheme.typography.headlineMedium)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pagerState.currentPage < pages.lastIndex) {
                TextButton(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pages.lastIndex)
                    }
                }) {
                    Text(text = "Skip", color = Color.Blue)
                }
            }
            if (pagerState.currentPage == pages.lastIndex) {
                val context = LocalContext.current
                val onBoardingManager = remember { OnBoardingManager(context) }
                val coroutineScope = rememberCoroutineScope()

                Button(onClick = {
                    coroutineScope.launch {
                        onBoardingManager.setOnBoardingCompleted(true)
                    }
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnBoarding.route) { inclusive = true }
                    }
                }) {
                    Text(text = "Get Started")
                }
            } else {
                Button(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }) {
                    Text(text = "Next")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardingScreenPreview() {
    OnBoardingScreen(rememberNavController())
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingScreenPreviewDark() {
    OnBoardingScreen(rememberNavController())
}