package com.thecode007.gitgooglerpo.ui

import com.thecode007.gitgooglerpo.domain.model.Repo

//
// Created by ASafieddine on 5/28/2022.
//

sealed class UiModel{
    data class Success(val repos:List<Repo>):UiModel()
    data class Error(val message:String):UiModel()
    object Loading:UiModel()
}