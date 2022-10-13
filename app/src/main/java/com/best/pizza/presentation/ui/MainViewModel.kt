package com.best.pizza.presentation.ui

import android.util.Log
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.domain.usecase.ProductsUseCases
import com.best.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases
) : ViewModel(){
    init{
        viewModelScope.launch {
            /*
            productsUseCases.getProductList(fetchFromRemote = true).collect{
                if(it is Resource.Success){
                    Log.d("EEE1",it.data.toString())
                }
            }

             */
            /*
            true-swipe refresh
            false -default
             */
            productsUseCases.getProductList(fetchFromRemote = false).collect{
                if(it is Resource.Success){
                    Log.d("EEE2",(it.data == null).toString())
                    Log.d("EEE2",it.data.toString())
                }
            }
            productsUseCases.getOtherInfo().collect{
                if(it is Resource.Success){
                    Log.d("EEE3",it.data.toString())
                }
            }
        }
    }
}