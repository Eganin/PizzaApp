package com.best.pizza.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.best.pizza.presentation.ui.theme.AppTheme
import com.best.pizza.presentation.ui.theme.PizzaTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzaTheme {
                val systemUiController = rememberSystemUiController()
                val statusBarColor = AppTheme.colors.primaryBackground
                val navController: NavHostController = rememberNavController()
                SideEffect {
                    // setup status bar
                    systemUiController.apply {
                        setSystemBarsColor(color = statusBarColor)
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.primaryBackground
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomBar(navController = navController)
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = DestinationsPage.FoodPage.name,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(AppTheme.colors.primaryBackground)
                        ) {
                            composable(DestinationsPage.FoodPage.name){

                            }
                            composable(DestinationsPage.PersonPage.name){

                            }
                            composable(DestinationsPage.BasketPage.name){

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PizzaTheme {
        Greeting("Android")
    }
}