package com.example.animationsdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class MainViewModel : ViewModel() {

    val list = listOf("item1", "item2", "item3", "item4")

}