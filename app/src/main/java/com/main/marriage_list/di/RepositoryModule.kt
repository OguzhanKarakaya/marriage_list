package com.main.marriage_list.di

import com.main.marriage_list.repository.homepage.HomePageRepository
import com.main.marriage_list.repository.homepage.HomePageRepositoryImpl
import com.main.marriage_list.repository.login.LoginRepository
import com.main.marriage_list.repository.login.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun loginRepository(repositoryImpl: LoginRepositoryImpl): LoginRepository = repositoryImpl

    @Provides
    fun homePageRepository(repositoryImpl: HomePageRepositoryImpl): HomePageRepository =
        repositoryImpl
}