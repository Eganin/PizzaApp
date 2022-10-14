package com.best.pizza.presentation.ui.screens.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.best.pizza.presentation.ui.screens.food.views.FoodToolBar
import com.best.pizza.presentation.ui.screens.food.views.ProgressBarAndError
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FoodPage(foodViewModel: FoodViewModel) {
    LaunchedEffect(key1 = Unit) {
        foodViewModel.init()
    }

    val swipeRefreshState =
        rememberSwipeRefreshState(isRefreshing = foodViewModel.state.isRefreshing)
    val state = foodViewModel.state

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            foodViewModel.onEvent(event = FoodPageEvent.Refresh)
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            state.otherInfo?.let {
                FoodToolBar(
                    cityName = it.cityName,
                    modifier = Modifier
                        .padding(top = 42.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }

    ProgressBarAndError(state = state)
}

