package com.example.newsapp.dataSource.sourcesDataSource

import com.example.newsapp.data.api.sourcesResponse.Source

interface SourcesDataSource {
    suspend fun getSource() : List<Source?>?
}