package com.thecode007.gitgooglerpo.ui.repositoryList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.thecode007.gitgooglerpo.R
import com.thecode007.gitgooglerpo.databinding.FragmentRepositoryListBinding
import com.thecode007.gitgooglerpo.ui.main.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RepositoryListFragment : Fragment() {

    private lateinit var  viewModel: MainViewModel

    private lateinit var binding: FragmentRepositoryListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagingAdapter = RepoAdapter(RepoComparator){
            viewModel.selectedRepoState.value = it
            findNavController().navigate(R.id.action_repositoryListFragment_to_repositoryDetailsFragment)
        }

        binding.bindList(pagingAdapter)
        binding.bindPageStates(pagingAdapter)
        binding.bindUiEvents(pagingAdapter)


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flowUi.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun FragmentRepositoryListBinding.bindList(pagingAdapter: RepoAdapter,
    ){
        rvRepos.layoutManager = LinearLayoutManager(root.context)
        rvRepos.adapter = pagingAdapter.withLoadStateFooter(
            footer = RepoListLoadStateAdapter(pagingAdapter::retry)
        )
    }



    // for handling fragment loader in the first load, or  error showing
    private fun FragmentRepositoryListBinding.bindPageStates(pagingAdapter: RepoAdapter) {
        pagingAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading){
                retryButton.isVisible = false
                progressBar.isVisible = true
            }
            else {
                progressBar.isVisible = false
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    retryButton.isVisible = true
                    Toast.makeText(root.context, it.error.toString(), Toast.LENGTH_SHORT).show()
                }
                if (errorState == null) {
                    rlState.isVisible = false
                    rvRepos.isVisible = true
                }
            }
        }
    }

    private fun FragmentRepositoryListBinding.bindUiEvents(pagingAdapter: RepoAdapter) {
        retryButton.setOnClickListener{
            pagingAdapter.retry()
        }
        etSearch.addTextChangedListener {
            viewModel.queryFlow.value = it.toString()
            rvRepos.scrollToPosition(0)
        }

    }

}