package com.thecode007.gitgooglerpo.data.repository

import com.thecode007.gitgooglerpo.data.remote.GitGoogleRepoService
import com.thecode007.gitgooglerpo.data.remote.dto.RepoDTO
import com.thecode007.gitgooglerpo.domain.repository.IGitGoogleRepoRepository
import javax.inject.Inject

class GitGoogleReposRepository @Inject constructor(private val api: GitGoogleRepoService):IGitGoogleRepoRepository {
    override suspend fun fetchRepos(): List<RepoDTO> {
        return api.fetchRepos(1,20)
    }
}