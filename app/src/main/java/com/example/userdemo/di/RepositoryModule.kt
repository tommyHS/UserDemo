package com.example.userdemo.di

import com.example.userdemo.data.repository.DefaultRepository
import com.example.userdemo.data.repository.Repository
import com.example.userdemo.network.UsersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun providesRepo(apiService: UsersApiService): Repository = DefaultRepository(apiService)
}
