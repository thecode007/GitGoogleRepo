package com.thecode007.gitgooglerpo.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecode007.gitgooglerpo.data.repository.GitGoogleReposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import javax.inject.Inject

//
// Created by ASafieddine on 5/28/2022.
//
class SplashViewModel  :ViewModel() {
     lateinit var splashFlow: Flow<Boolean>

    init {
        viewModelScope.launch {
            splashFlow = flow {
                emit(false)
                delay(3000)
                emit(true)
            }.distinctUntilChanged()
            .stateIn(viewModelScope, WhileSubscribed(5000), false)
        }
    }
}