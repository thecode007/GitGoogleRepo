package com.thecode007.gitgooglerpo.ui.repositoryDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.thecode007.gitgooglerpo.R
import com.thecode007.gitgooglerpo.databinding.FragmentRepositoryDetailsBinding
import com.thecode007.gitgooglerpo.ui.main.MainViewModel


class RepositoryDetailsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding:FragmentRepositoryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding = FragmentRepositoryDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repo = viewModel.selectedRepoState.value

        if (repo != null) {
            binding.sdImage.setImageURI(repo.avatarUrl)
            binding.tvName.text = repo.repoName
            binding.tvCreatedAt.text = repo.creationDate
            binding.tvStarcount.text = repo.stargazers_count.toString()

        }

    }

}