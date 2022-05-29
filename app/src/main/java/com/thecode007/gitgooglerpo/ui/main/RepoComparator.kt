package com.thecode007.gitgooglerpo.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.thecode007.gitgooglerpo.domain.model.Repo


//
// Created by ASafieddine on 5/28/2022.
//
object RepoComparator : DiffUtil.ItemCallback<Repo>() {
  override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
    return oldItem == newItem
  }
}