package com.example.newsapp.data.dataSourceImp.newsDataSourcesImpl

import com.example.newsapp.data.api.WebServices
import com.example.newsapp.data.api.newsResponse.NewsItem
import com.example.newsapp.dataSource.newsDataSource.NewsDataSource
import javax.inject.Inject

class  NewsOnlineDataSourcesImpl @Inject constructor
    (private val webServices: WebServices)
    : NewsDataSource{
    override suspend fun getNewsSources(sourceId: String?): List<NewsItem?>? {
        val response = webServices.getNewsResponse(sources = sourceId)
        return response.articles
    }
}