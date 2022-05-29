package com.thecode007.gitgooglerpo.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thecode007.gitgooglerpo.data.remote.GitGoogleRepoService
import com.thecode007.gitgooglerpo.data.remote.RepoNetworkMapper
import com.thecode007.gitgooglerpo.domain.model.Repo
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

//
// Created by ASafieddine on 5/28/2022.
//
class RepoPagingSource @Inject constructor(private val apiService: GitGoogleRepoService):
    PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val position = params.key ?: 1
        return try {
            val response = apiService.fetchRepos( position, params.loadSize)
            val repos = response.map {
                repoDTO -> RepoNetworkMapper.mapToDomainModel(repoDTO)
            }
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                position + (params.loadSize / 30)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}