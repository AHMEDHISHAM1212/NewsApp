package com.example.newsapp.data.repositoryImpl.newsSourcesRepoImpl

import com.example.newsapp.data.api.newsResponse.NewsItem
import com.example.newsapp.dataSource.newsDataSource.NewsDataSource
import com.example.newsapp.repository.newsRepository.NewsSourcesRepository
import javax.inject.Inject

class NewsSourcesRepositoryImpl @Inject constructor
    (private val onlineNewsDataSource : NewsDataSource)
    : NewsSourcesRepository{
    override suspend fun getNews(sourceId: String?): List<NewsItem?>? {
        return onlineNewsDataSource.getNewsSources(sourceId)
    }
}