package com.thecode007.gitgooglerpo.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thecode007.gitgooglerpo.BuildConfig
import com.thecode007.gitgooglerpo.data.remote.GitGoogleRepoService
import com.thecode007.gitgooglerpo.data.repository.GitGoogleReposRepository
import com.thecode007.gitgooglerpo.domain.repository.IGitGoogleRepoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//
// Created by ASafieddine on 5/28/2022.
//

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor( logger)
            .callTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .header("Authorization", "Bearer ".plus(BuildConfig.TOKEN))
                    .header("accept", "application/vnd.github+json")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideReposApi(builder: Retrofit.Builder): GitGoogleRepoService {
        return builder.build().create(GitGoogleRepoService::class.java)
    }

    @Provides
    @Singleton
    fun provideGitGoogleReposRepository(api: GitGoogleRepoService): IGitGoogleRepoRepository {
        return GitGoogleReposRepository(api)
    }
}