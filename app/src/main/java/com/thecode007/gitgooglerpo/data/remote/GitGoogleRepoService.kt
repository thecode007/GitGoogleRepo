package com.thecode007.gitgooglerpo.data.remote

import com.thecode007.gitgooglerpo.data.remote.dto.RepoDTO
import retrofit2.http.GET
import retrofit2.http.Query

//
// Created by ASafieddine on 5/28/2022.
//
interface GitGoogleRepoService {
    @GET("google/repos/")
    suspend fun fetchRepos(@Query("page") nextPage: Int,
                           @Query("per_page")  pageSize:Int): List<RepoDTO>
}