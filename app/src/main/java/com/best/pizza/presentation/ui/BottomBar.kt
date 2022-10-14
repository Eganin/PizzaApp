package com.best.pizza.presentation.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.best.pizza.R

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
                    GetIconForBottomBar(screen = screen.name, isSelected = isSelected)
                })
        }
    }
}

@Composable
private fun GetIconForBottomBar(screen: String, isSelected: Boolean = false) {
    return when (screen) {
        DestinationsPage.FoodPage.name -> {
            Icon(
                painter = painterResource(id = R.drawable.food_icon),
                contentDescription = stringResource(R.string.food_description)
            )
        }

        DestinationsPage.PersonPage.name -> {
            Icon(
                painter = painterResource(id = R.drawable.person_icon),
                contentDescription = stringResource(R.string.person_description),
            )
        }

        DestinationsPage.BasketPage.name -> {
            Icon(
                painter = painterResource(id = R.drawable.basket_icon),
                contentDescription = stringResource(R.string.basket_description),
            )
        }

        else -> {}
    }
}

enum class DestinationsPage {
    FoodPage,
    PersonPage,
    BasketPage
}