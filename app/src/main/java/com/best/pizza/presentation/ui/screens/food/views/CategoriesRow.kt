package com.best.pizza.presentation.ui.screens.food.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.best.pizza.presentation.ui.theme.AppTheme
import com.best.pizza.presentation.ui.theme.PizzaTheme
import com.best.pizza.presentation.ui.theme.Typography

@Composable
internal fun CategoriesRow(categories: List<String>, modifier: Modifier = Modifier) {
    LazyRow(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        categories.indices.forEach { i ->
            item {
                if (i == categories.size - 1) {
                    CategoryCells(
                        title = categories[i],
                        modifier = Modifier.padding(end = 16.dp),
                        index = i
                    )
                } else {
                    CategoryCells(title = categories[i], index = i)
                }
            }
        }
    }
}

@Composable
private fun CategoryCells(
    title: String,
    modifier: Modifier = Modifier,
    index :Int
) {
    var isSelected by remember { mutableStateOf(index==0) }
    Card(
        modifier = modifier
            .padding(start = 8.dp)
            .clickable {
                isSelected = !isSelected
            },
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = if (isSelected) AppTheme.colors.secondaryBackground else AppTheme.colors.primaryBackground
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

