package com.thecode007.gitgooglerpo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//
// Created by ASafieddine on 5/28/2022.
//

@HiltViewModel
class MainViewModel @Inject constructor():ViewModel() {

    private val _isSplashLoading = MutableStateFlow(true)
     val isSplashLoading = _isSplashLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _isSplashLoading.value = false
        }
    }

}