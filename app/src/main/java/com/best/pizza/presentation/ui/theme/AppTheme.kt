package com.best.pizza.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(
    val primaryText : Color,
    val primaryBackground: Color,
    val secondaryText : Color,
    val secondaryBackground : Color,
    val categoryText : Color,
    val bottomBarText:Color,
    val tintColor: Color,
    val errorColor: Color,
)

object AppTheme{
    val colors : AppColors
        @Composable
        get() = LocalAppColors.current
}

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No colors providers")
}