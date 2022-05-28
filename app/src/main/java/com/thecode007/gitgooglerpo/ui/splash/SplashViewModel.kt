package com.thecode007.gitgooglerpo.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

//
// Created by ASafieddine on 5/28/2022.
//
class SplashViewModel:ViewModel() {


     lateinit var splashFlow: Flow<Boolean>
     private lateinit var timerEndedFlow:Flow<Boolean>
     private lateinit var starterTasksEndedFlow:Flow<Boolean>

    init {
        viewModelScope.launch {
            timerEndedFlow = flow {
                emit(false)
                delay(3000)
                emit(true)
            }
            starterTasksEndedFlow = flow {
                emit(false)
                // mimics database creation
                delay(5000)
                emit(true)
            }

            splashFlow = timerEndedFlow.combine(starterTasksEndedFlow) {
                    isTimerFlowEnded, isStaterTasksEnded -> isTimerFlowEnded and isStaterTasksEnded
            }.distinctUntilChanged()
        }
    }
}