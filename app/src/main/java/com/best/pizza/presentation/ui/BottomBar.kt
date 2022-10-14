package com.best.pizza.presentation.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.best.pizza.R
import com.best.pizza.presentation.ui.screens.food.views.FoodToolBar
import com.best.pizza.presentation.ui.theme.AppTheme
import com.best.pizza.presentation.ui.theme.PizzaTheme
import com.best.pizza.presentation.ui.theme.Typography

@Composable
fun BottomBar(navController: NavController) {
    val bottomItems = listOf(
        DestinationsPage.FoodPage,
        DestinationsPage.PersonPage,
        DestinationsPage.BasketPage
    )

    BottomNavigation(backgroundColor = Color.White) {
        bottomItems.forEach { screen ->
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val isSelected = navBackStackEntry?.destination?.hierarchy
                ?.any { it.route == screen.name } == true
            BottomNavigationItem(
                selected = false,
                onClick = { navController.navigate(screen.name) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        GetIconForBottomBar(screen = screen.name, isSelected = isSelected)
                        GetTextForBottomBar(screen = screen.name, isSelected = isSelected)
                    }
                })
        }
    }
}

@Composable
private fun GetTextForBottomBar(screen: String, isSelected: Boolean = false) {
    return when (screen) {
        DestinationsPage.FoodPage.name -> {
            BottomBarText(title = stringResource(R.string.menu), isSelected = isSelected)
        }

        DestinationsPage.PersonPage.name -> {
            BottomBarText(title = stringResource(R.string.profile), isSelected = isSelected)
        }

        DestinationsPage.BasketPage.name -> {
            BottomBarText(title = stringResource(R.string.basket), isSelected = isSelected)
        }

        else -> {}
    }
}

@Composable
private fun BottomBarText(title: String, isSelected: Boolean) {
    Text(
        text = title,
        style = Typography.body1,
        color = if (!isSelected) AppTheme.colors.bottomBarText else AppTheme.colors.tintColor
    )
}

@Composable
private fun GetIconForBottomBar(screen: String, isSelected: Boolean = false) {
    return when (screen) {
        DestinationsPage.FoodPage.name -> {
            BottomBarIcon(
                drawableId = R.drawable.food_icon,
                stringId = R.string.food_description,
                isSelected = isSelected
            )
        }

        DestinationsPage.PersonPage.name -> {
            BottomBarIcon(
                drawableId = R.drawable.person_icon,
                stringId = R.string.person_description,
                isSelected = isSelected
            )
        }

        DestinationsPage.BasketPage.name -> {
            BottomBarIcon(
                drawableId = R.drawable.basket_icon,
                stringId = R.string.basket_description,
                isSelected = isSelected
            )
        }

        else -> {}
    }
}

@Composable
private fun BottomBarIcon(
    @DrawableRes drawableId: Int,
    @StringRes stringId: Int,
    isSelected: Boolean
) {
    Icon(
        painter = painterResource(id = drawableId),
        contentDescription = stringResource(id = stringId),
        tint = if (isSelected) AppTheme.colors.tintColor else AppTheme.colors.bottomBarText
    )
}

enum class DestinationsPage {
    FoodPage,
    PersonPage,
    BasketPage
}
@Preview(showBackground = true)
@Composable
private fun BottomBarItemIsSelectedPreview() {
    PizzaTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GetIconForBottomBar(screen = DestinationsPage.FoodPage.name, isSelected = true)
            GetTextForBottomBar(screen = DestinationsPage.FoodPage.name, isSelected = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomBarItemIsNotSelectedPreview() {
    PizzaTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GetIconForBottomBar(screen = DestinationsPage.FoodPage.name, isSelected = false)
            GetTextForBottomBar(screen = DestinationsPage.FoodPage.name, isSelected = false)
        }
    }
}