package com.thecode007.gitgooglerpo.domain.repository

import com.thecode007.gitgooglerpo.data.remote.dto.RepoDTO

//
// Created by ASafieddine on 5/28/2022.
//
interface IGitGoogleRepoRepository {
    suspend fun fetchRepos(): List<RepoDTO>
}