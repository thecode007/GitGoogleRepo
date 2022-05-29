package com.thecode007.gitgooglerpo.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thecode007.gitgooglerpo.data.repository.RepoPagingSource
import com.thecode007.gitgooglerpo.domain.model.Repo
import kotlinx.coroutines.flow.Flow

//
// Created by ASafieddine on 5/28/2022.
//
interface IGitGoogleRepoRepository {
    suspend fun fetchRepos(): Flow<PagingData<Repo>>
}