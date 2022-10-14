package com.best.pizza.presentation.ui.screens.food.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.best.pizza.presentation.ui.screens.food.FoodPageState
import com.best.pizza.presentation.ui.theme.AppTheme
import com.best.pizza.presentation.ui.theme.PizzaTheme
import com.best.pizza.presentation.ui.theme.Typography

@Composable
fun ProgressBarAndError(state: FoodPageState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(color = AppTheme.colors.tintColor)
        } else if (state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                style = Typography.body1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressBarAndErrorIfLoadingPreview() {
    PizzaTheme {
        ProgressBarAndError(state = FoodPageState(isLoading = true))
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressBarAndErrorIfErrorMessagePreview() {
    PizzaTheme {
        ProgressBarAndError(state = FoodPageState(error = "Unknown error"))
    }
}