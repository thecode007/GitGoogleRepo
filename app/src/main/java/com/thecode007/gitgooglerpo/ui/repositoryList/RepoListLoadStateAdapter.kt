package com.thecode007.gitgooglerpo.ui.repositoryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.thecode007.gitgooglerpo.databinding.LayoutLoadStateBinding


//
// Created by ASafieddine on 5/28/2022.
//
class RepoListLoadStateAdapter(
  private val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    loadState: LoadState
  ) = LoadStateViewHolder(LayoutLoadStateBinding.inflate(LayoutInflater.from(parent.context)), retry)

  override fun onBindViewHolder(
      holder: LoadStateViewHolder,
      loadState: LoadState
  ) = holder.bind(loadState)
}