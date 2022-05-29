package com.thecode007.gitgooglerpo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thecode007.gitgooglerpo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRepos.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.rvRepos.addItemDecoration(decoration)
        val pagingAdapter = RepoAdapter(RepoComparator)
        binding.rvRepos.adapter = pagingAdapter.withLoadStateFooter(
            footer = RepoListLoadStateAdapter(pagingAdapter::retry)
        )

        // adding load state listener to handle the initial load case, inorder to show outer loader states
        pagingAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading){
                binding.retryButton.isVisible = false
                binding.progressBar.isVisible = true
            }
            else {
                binding.progressBar.isVisible = false
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    binding.retryButton.isVisible = true
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_SHORT).show()
                }
                if (errorState == null) {
                    binding.rlState.isVisible = false
                    binding.rvRepos.isVisible = true
                }
            }
        }

        binding.retryButton.setOnClickListener{
            pagingAdapter.retry()
        }

        binding.btnGo.setOnClickListener{
            binding.rvRepos.scrollToPosition(0)
            viewModel.queryFlow.value = binding.etSearch.text.toString()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.flowUi.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}