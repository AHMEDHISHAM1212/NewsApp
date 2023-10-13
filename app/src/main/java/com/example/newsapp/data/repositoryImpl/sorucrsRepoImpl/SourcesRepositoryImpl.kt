package com.example.newsapp.data.repositoryImpl.sorucrsRepoImpl

import com.example.newsapp.data.api.sourcesResponse.Source
import com.example.newsapp.dataSource.sourcesDataSource.SourcesDataSource
import com.example.newsapp.repository.sourcesRepository.SourceRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(private val sourcesDataSource: SourcesDataSource)
    : SourceRepository{
    override suspend fun getSources(): List<Source?>? {
        return sourcesDataSource.getSource()
    }
}