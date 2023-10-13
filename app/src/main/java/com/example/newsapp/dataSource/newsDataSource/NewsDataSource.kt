package com.example.newsapp.dataSource.newsDataSource

import com.example.newsapp.data.api.newsResponse.NewsItem

interface NewsDataSource {
    suspend fun getNewsSources(sourceId: String?) : List<NewsItem?>?
}