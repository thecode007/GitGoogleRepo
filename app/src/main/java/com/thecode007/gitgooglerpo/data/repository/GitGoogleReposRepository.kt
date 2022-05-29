package com.thecode007.gitgooglerpo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thecode007.gitgooglerpo.data.remote.GitGoogleRepoService
import com.thecode007.gitgooglerpo.domain.model.Repo
import com.thecode007.gitgooglerpo.domain.repository.IGitGoogleRepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitGoogleReposRepository @Inject constructor(private val service: GitGoogleRepoService
):IGitGoogleRepoRepository {

    override suspend fun fetchRepos(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                RepoPagingSource(service)
            }
        ).flow
    }
}
