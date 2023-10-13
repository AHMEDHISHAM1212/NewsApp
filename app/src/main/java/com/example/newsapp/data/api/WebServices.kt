package com.example.newsapp.data.api

import com.example.newsapp.data.api.newsResponse.NewsResponse
import com.example.newsapp.data.api.sourcesResponse.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
     suspend fun getSourceResponse(
        @Query("apiKey") apiKey : String = ApiConstants.API_KEY
    ): SourceResponse

    @GET("v2/everything")
    suspend fun getNewsResponse(
        @Query("apiKey") apiKey : String = ApiConstants.API_KEY,
        @Query("sources") sources: String?,
        @Query("pageSize") pageSize : Int? = null,
        @Query("page") page:Int? = null
    ): NewsResponse
}