package com.thecode007.gitgooglerpo.domain.util

//
// Created by ASafieddine on 5/28/2022.
//
interface DomainMapper<DTOModel, DomainModel> {
    fun mapToDomainModel(dto:DTOModel):DomainModel
}