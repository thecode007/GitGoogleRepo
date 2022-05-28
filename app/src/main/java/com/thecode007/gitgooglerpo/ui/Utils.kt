package com.thecode007.gitgooglerpo.ui

import com.thecode007.gitgooglerpo.data.remote.dto.RepoDTO

//
// Created by ASafieddine on 5/28/2022.
//

sealed class UiModel{
    data class Success(val repos:List<RepoDTO>):UiModel()
    data class Error(val message:String):UiModel()
    object Loading:UiModel()
}