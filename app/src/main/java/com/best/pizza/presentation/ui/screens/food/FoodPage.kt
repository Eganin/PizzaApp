package com.best.pizza.presentation.ui.screens.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.best.pizza.presentation.ui.screens.food.views.CategoriesRow
import com.best.pizza.presentation.ui.screens.food.views.DiscountImagesRow
import com.best.pizza.presentation.ui.screens.food.views.FoodToolBar
import com.best.pizza.presentation.ui.screens.food.views.ProductList
import com.best.pizza.presentation.ui.screens.food.views.ProgressBarAndError
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
internal fun FoodPage(foodViewModel: FoodViewModel) {
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
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        CollapsingToolbarScaffold(
            state = rememberCollapsingToolbarScaffoldState(), // provide the state of the scaffold
            toolbar = {
                DiscountImagesRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 97.dp)
                )
                state.otherInfo?.let {
                    FoodToolBar(
                        cityName = it.cityName,
                        modifier = Modifier
                            .padding(top = 42.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                    )
                }
            },
            modifier = Modifier.fillMaxSize(),
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
                state.otherInfo?.let {
                    CategoriesRow(
                        categories = it.categories,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth()
                    )
                }
                ProductList(productInfo = state.productInfo, modifier = Modifier.fillMaxWidth())
            }
        }
    }

    ProgressBarAndError(state = state)
}

