package com.best.pizza.presentation.ui.screens.food.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.best.pizza.R
import com.best.pizza.presentation.ui.theme.PizzaTheme

@Composable
internal fun DiscountImagesRow(modifier: Modifier = Modifier) {
    LazyRow(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        item {
            Image(
                painter = painterResource(id = R.drawable.discount_image_1),
                contentDescription = stringResource(R.string.discount_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .defaultMinSize(minWidth = 300.dp, minHeight = 112.dp)
                    .width(300.dp)
                    .height(112.dp)
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.discount_image_2),
                contentDescription = stringResource(R.string.discount_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end=16.dp)
                    .defaultMinSize(minWidth = 290.dp, minHeight = 102.dp)
                    .width(290.dp)
                    .height(102.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DiscountImagesRowPreview() {
    PizzaTheme {
        DiscountImagesRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp)
        )
    }
}