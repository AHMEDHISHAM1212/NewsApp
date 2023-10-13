package com.example.newsapp.repository.newsRepository

import com.example.newsapp.data.api.newsResponse.NewsItem

interface NewsSourcesRepository {
    suspend fun getNews(sourceId: String?): List<NewsItem?>?
}