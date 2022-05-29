package com.thecode007.gitgooglerpo

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp

//
// Created by ASafieddine on 5/28/2022.
//

@HiltAndroidApp
class GitGoogleRepoApplication:Application() {

    companion object {
        var appInstance: GitGoogleRepoApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        appInstance = this
    }
}