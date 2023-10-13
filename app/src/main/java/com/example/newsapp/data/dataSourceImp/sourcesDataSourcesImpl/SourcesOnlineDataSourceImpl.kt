package com.example.newsapp.data.dataSourceImp.sourcesDataSourcesImpl

import com.example.newsapp.data.api.WebServices
import com.example.newsapp.data.api.sourcesResponse.Source
import com.example.newsapp.dataSource.sourcesDataSource.SourcesDataSource

class SourcesOnlineDataSourceImpl(private val webServices: WebServices)
    : SourcesDataSource {
    override suspend fun getSource(): List<Source?>? {
        val response = webServices.getSourceResponse()
        return response.sources
    }
}