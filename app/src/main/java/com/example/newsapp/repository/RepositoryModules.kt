package com.example.newsapp.repository

import com.example.newsapp.data.dataSourceImp.newsDataSourcesImpl.NewsOnlineDataSourcesImpl
import com.example.newsapp.data.dataSourceImp.sourcesDataSourcesImpl.SourcesOnlineDataSourceImpl
import com.example.newsapp.data.repositoryImpl.newsSourcesRepoImpl.NewsSourcesRepositoryImpl
import com.example.newsapp.data.repositoryImpl.sorucrsRepoImpl.SourcesRepositoryImpl
import com.example.newsapp.dataSource.newsDataSource.NewsDataSource
import com.example.newsapp.dataSource.sourcesDataSource.SourcesDataSource
import com.example.newsapp.repository.newsRepository.NewsSourcesRepository
import com.example.newsapp.repository.sourcesRepository.SourceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModules {

    @Binds
    abstract fun provideSourcesRepository(
        sourcesRepositoryImpl: SourcesRepositoryImpl
    ):SourceRepository

    @Binds
    abstract fun provideSourcesDataSource(
        sourcesImpl: NewsOnlineDataSourcesImpl
    ):SourcesDataSource

    @Binds
    abstract fun provideNewsRepository(
        newsSourcesRepositoryImpl: NewsSourcesRepositoryImpl
    ):NewsSourcesRepository

    @Binds
    abstract fun provideNewsDataSource(
        newsOnlineDataSourcesImpl: NewsOnlineDataSourcesImpl
    ):NewsDataSource
}