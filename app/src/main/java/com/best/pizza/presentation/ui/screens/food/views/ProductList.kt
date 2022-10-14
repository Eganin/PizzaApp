package com.best.pizza.presentation.ui.screens.food.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.best.domain.models.ProductInfo
import com.best.pizza.R
import com.best.pizza.presentation.ui.theme.AppTheme
import com.best.pizza.presentation.ui.theme.PizzaTheme
import com.best.pizza.presentation.ui.theme.Typography

@Composable
fun ProductList(productInfo: List<ProductInfo>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        productInfo.forEach {
            item { ProductCells(productInfo = it, modifier = modifier) }
        }
    }
}

@Composable
private fun ProductCells(productInfo: ProductInfo, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(modifier = modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = productInfo.imageLink,
                contentDescription = null,
                modifier = Modifier.size(135.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(22.dp))
            Column(modifier = modifier) {
                Text(
                    text = productInfo.name,
                    style = Typography.body1,
                    color = AppTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = productInfo.description,
                    style = Typography.subtitle1,
                    color = AppTheme.colors.secondaryText
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.End),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(width = 1.dp, color = AppTheme.colors.tintColor)
                ) {
                    Text(
                        text = stringResource(R.string.suffix_price)
                                + " ${productInfo.price} "
                                + stringResource(R.string.currency_price),
                        style = Typography.subtitle1,
                        color = AppTheme.colors.tintColor,
                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(16.dp))
        Divider(modifier = modifier.height(1.dp), color = AppTheme.colors.secondaryText)
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCellsPreview() {
    PizzaTheme {
        ProductCells(
            productInfo = ProductInfo(
                name = "Pizza",
                description = "description pizza",
                price = "345",
                imageLink = "https://foodish-api.herokuapp.com/images/pizza/pizza25.jpg"
            )
        )
    }
}