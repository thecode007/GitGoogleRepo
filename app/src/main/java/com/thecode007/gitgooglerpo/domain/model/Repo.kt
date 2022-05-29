package com.thecode007.gitgooglerpo.domain.model


//
// Created by ASafieddine on 5/28/2022.
//

data class Repo(
    val id:Long,
    val repoName:String,
    val creationDate:String,
    val avatarUrl:String,
    val stargazers_count:Int)