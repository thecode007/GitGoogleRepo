package com.thecode007.gitgooglerpo.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.thecode007.gitgooglerpo.R
import com.thecode007.gitgooglerpo.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

// Didn't use the latest splash api since it had some bugs, sometime the icon don't show especially when launched
// from Android Studio
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val splashViewModel:SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launchWhenCreated {
            splashViewModel.splashFlow.collectLatest {
                if (it) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finishAffinity()
                }
            }
        }
    }
}