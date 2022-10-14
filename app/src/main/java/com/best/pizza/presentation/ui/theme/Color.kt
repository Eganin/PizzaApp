package com.best.pizza.presentation.ui.theme

import androidx.compose.ui.graphics.Color

val LightTitleColor = Color(0xFF222831)
val DarkTitleColor = Color(0xFFE8ECF1)
val CategoriesColor = Color(0xFFC3C4C9)
val SecondaryTextColor = Color(0xFFAAAAAD)
val TintColor = Color(0xFFFD3A69)
val BottomBarColor = Color(0xFF7B7B7B)
val LightBackgroundColor = Color(0xFFFBFBFB)
val DarkBackgroundColor = Color(0xFF191919)
val SecondaryBackgroundColor = Color(0xFFF4CDD6)

val baseLightPalette = AppColors(
    primaryText = LightTitleColor,
    primaryBackground = LightBackgroundColor,
    secondaryText = SecondaryTextColor,
    secondaryBackground = SecondaryBackgroundColor,
    categoryText = CategoriesColor,
    bottomBarText = BottomBarColor,
    tintColor = TintColor,
    errorColor = Color.Red
)

val baseDarkPalette = AppColors(
    primaryText = DarkTitleColor,
    primaryBackground = DarkBackgroundColor,
    secondaryText = SecondaryTextColor,
    secondaryBackground = SecondaryBackgroundColor,
    categoryText = CategoriesColor,
    bottomBarText = BottomBarColor,
    tintColor = TintColor,
    errorColor = Color.Red
)