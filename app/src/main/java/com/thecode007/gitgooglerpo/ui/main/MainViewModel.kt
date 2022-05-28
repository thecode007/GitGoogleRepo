package com.thecode007.gitgooglerpo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecode007.gitgooglerpo.domain.usecase.FetchReposUseCase
import com.thecode007.gitgooglerpo.ui.UiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

//
// Created by ASafieddine on 5/28/2022.
//

@HiltViewModel
class MainViewModel @Inject constructor(val usecase: FetchReposUseCase):ViewModel() {


    lateinit var flowUi:Flow<UiModel>
    init {
        viewModelScope.launch {
            flowUi =  usecase()
                .distinctUntilChanged()
                .stateIn(
                this,
                SharingStarted.WhileSubscribed(5000),
                UiModel.Loading
            )
        }
    }

}