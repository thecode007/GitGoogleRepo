package com.thecode007.gitgooglerpo.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.thecode007.gitgooglerpo.databinding.LayoutRepoItemBinding
import com.thecode007.gitgooglerpo.domain.model.Repo

//
// Created by ASafieddine on 5/28/2022.
//
class RepoViewHolder(val binding:LayoutRepoItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(repo: Repo) {
        binding.tvName.text = repo.repoName
        binding.tvCreatedAt.text = repo.creationDate
        binding.sdRepo.setImageURI(repo.avatarUrl)
    }
}