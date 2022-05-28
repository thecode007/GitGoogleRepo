package com.thecode007.gitgooglerpo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.thecode007.gitgooglerpo.R
import com.thecode007.gitgooglerpo.ui.UiModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenCreated {
            viewModel.flowUi.collect {
            when (it) {
                is UiModel.Loading -> Log.wtf("UiModeler --->", "IsLoading")
                is UiModel.Error -> Log.wtf("UiModeler --->", it.message)
                is UiModel.Success -> Log.wtf("UiModeler --->", "Repos ${it.repos.size} are fetched")
            }
        }
        }
    }
}