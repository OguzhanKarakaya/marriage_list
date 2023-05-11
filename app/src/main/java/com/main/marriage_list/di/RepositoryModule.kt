package com.main.marriage_list.di

import com.main.marriage_list.repository.homepage.HomePageRepository
import com.main.marriage_list.repository.homepage.HomePageRepositoryImpl
import com.main.marriage_list.repository.login.LoginRepository
import com.main.marriage_list.repository.login.LoginRepositoryImpl
import com.main.marriage_list.repository.product.ProductRepository
import com.main.marriage_list.repository.product.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun loginRepository(repositoryImpl: LoginRepositoryImpl): LoginRepository = repositoryImpl

    @Provides
    fun homePageRepository(repositoryImpl: HomePageRepositoryImpl): HomePageRepository =
        repositoryImpl

    @Provides
    fun productRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository = repositoryImpl
}