package com.best.pizza.presentation.ui.screens.food.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.best.pizza.R
import com.best.pizza.presentation.ui.theme.AppTheme
import com.best.pizza.presentation.ui.theme.PizzaTheme
import com.best.pizza.presentation.ui.theme.Typography

@Composable
fun FoodToolBar(cityName: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = cityName, style = Typography.body1, color = AppTheme.colors.primaryText)
            Icon(
                painter = painterResource(id = R.drawable.arrow_down_icon),
                contentDescription = stringResource(R.string.arrow_down),
                tint = AppTheme.colors.primaryText,
                modifier = Modifier.padding(start = 14.dp)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.qr_code_icon),
            contentDescription = stringResource(R.string.qr_code),
            tint = AppTheme.colors.primaryText,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ToolBarPreview() {
    PizzaTheme {
        FoodToolBar(
            cityName = "Moskow",
            modifier = Modifier
                .padding(top = 42.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        )
    }
}