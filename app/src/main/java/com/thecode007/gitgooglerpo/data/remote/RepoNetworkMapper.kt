package com.thecode007.gitgooglerpo.data.remote

import com.thecode007.gitgooglerpo.data.remote.dto.RepoDTO
import com.thecode007.gitgooglerpo.domain.model.Repo
import com.thecode007.gitgooglerpo.domain.util.DomainMapper

//
// Created by ASafieddine on 5/28/2022.
//
object RepoNetworkMapper:DomainMapper<RepoDTO, Repo> {
    override fun mapToDomainModel(dto: RepoDTO): Repo {
        val formattedCreatedDate = dto.created_at.replace("T"," ")
            .replace("Z","")
        return Repo(dto.id,
            dto.name,
            formattedCreatedDate,
            dto.owner.avatar_url,
            dto.stargazers_count)
    }
}