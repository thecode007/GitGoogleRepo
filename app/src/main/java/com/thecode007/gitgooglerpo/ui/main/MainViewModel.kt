package com.thecode007.gitgooglerpo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.thecode007.gitgooglerpo.domain.model.Repo
import com.thecode007.gitgooglerpo.domain.repository.IGitGoogleRepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

//
// Created by ASafieddine on 5/28/2022.
//

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: IGitGoogleRepoRepository):ViewModel() {

    val queryFlow = MutableStateFlow("")
    lateinit var flowUi: Flow<PagingData<Repo>>

     var selectedRepoState: MutableStateFlow<Repo?> = MutableStateFlow(null)

    lateinit var  selectedRepoFlow: Flow<Repo?>

     init {
        viewModelScope.launch {
            flowUi = repository
                .fetchRepos()
                .cachedIn(this)
                .combine(queryFlow) { pagingData, query ->
                    pagingData.filter { repo ->
                        if (query.isEmpty())
                            return@filter true
                        repo.repoName.lowercase().startsWith(query.lowercase())
                    }
                }
                .cachedIn(this)
        }

        selectedRepoFlow = flow {
            emit(null)
        }.combine(selectedRepoState){ _, repo ->
            repo
        }
    }


}







