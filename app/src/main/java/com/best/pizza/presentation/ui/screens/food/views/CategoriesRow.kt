package com.best.pizza.presentation.ui.screens.food.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.best.pizza.presentation.ui.theme.AppTheme
import com.best.pizza.presentation.ui.theme.PizzaTheme
import com.best.pizza.presentation.ui.theme.Typography

@Composable
fun CategoriesRow(categories: List<String>, modifier: Modifier = Modifier) {
    LazyRow(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        categories.indices.forEach { i ->
            item {
                if (i == 0) {
                    CategoryCells(title = categories[i], isSelected = true)
                } else {
                    CategoryCells(title = categories[i], isSelected = false)
                }
            }
        }
    }
}

@Composable
fun CategoryCells(title: String, isSelected: Boolean) {
    Card(
        modifier = Modifier.padding(start = 8.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = if (isSelected) AppTheme.colors.secondaryBackground else Color.White
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 25.dp, end = 25.dp),
            text = title,
            style = Typography.body2.copy(fontWeight = FontWeight.Bold),
            color = if (isSelected) AppTheme.colors.tintColor else AppTheme.colors.categoryText
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoriesRowPreview() {
    PizzaTheme {
        CategoriesRow(
            categories = listOf("Пицца", "Комбо", "Десерты", "Напитки"),
            modifier = Modifier
                .padding(top = 24.dp, start = 8.dp)
                .fillMaxWidth()
        )
    }
}

