package com.thecode007.gitgooglerpo.domain.usecase

import com.thecode007.gitgooglerpo.data.remote.ApiFlowWrapper
import com.thecode007.gitgooglerpo.domain.repository.IGitGoogleRepoRepository
import com.thecode007.gitgooglerpo.ui.UiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//
// Created by ASafieddine on 5/28/2022.
//
class FetchReposUseCase @Inject constructor(private val repository: IGitGoogleRepoRepository)  {

    suspend operator fun invoke(): Flow<UiModel> {
        return ApiFlowWrapper().invoke {
            repository.fetchRepos()
        }
    }
}