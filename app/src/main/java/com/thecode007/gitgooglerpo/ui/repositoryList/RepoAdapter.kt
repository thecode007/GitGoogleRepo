package com.thecode007.gitgooglerpo.ui.repositoryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.thecode007.gitgooglerpo.databinding.LayoutRepoItemBinding
import com.thecode007.gitgooglerpo.domain.model.Repo

//
// Created by ASafieddine on 5/28/2022.
//
class RepoAdapter(diffCallback: DiffUtil.ItemCallback<Repo>, val OnRepoClick:(Repo)->Unit): PagingDataAdapter<Repo, RepoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(LayoutRepoItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo!!) {
            OnRepoClick(repo)
        }
    }


}