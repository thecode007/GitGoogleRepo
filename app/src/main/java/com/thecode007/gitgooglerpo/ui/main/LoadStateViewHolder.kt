package com.thecode007.gitgooglerpo.ui.main

import android.widget.Button
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.thecode007.gitgooglerpo.databinding.LayoutLoadStateBinding

//
// Created by ASafieddine on 5/28/2022.
//
class LoadStateViewHolder(
  private val binding: LayoutLoadStateBinding,
  retry: () -> Unit
) : RecyclerView.ViewHolder(
  binding.root
) {
  private val retry: Button = binding.retryButton
    .also {
      it.setOnClickListener { retry() }
    }
  fun bind(loadState: LoadState) {
    if (loadState is LoadState.Error) {
      binding.errorMsg.text = loadState.error.localizedMessage
    }
    binding.progressBar.isVisible = loadState is LoadState.Loading
    retry.isVisible = loadState is LoadState.Error
    binding.errorMsg.isVisible = loadState is LoadState.Error
  }
}