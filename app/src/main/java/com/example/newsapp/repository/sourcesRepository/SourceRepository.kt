package com.example.newsapp.repository.sourcesRepository

import com.example.newsapp.data.api.sourcesResponse.Source

interface SourceRepository {
    suspend fun getSources(): List<Source?>?
}