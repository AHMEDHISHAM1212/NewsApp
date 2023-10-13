package com.example.newsapp.data.api.newsResponse

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.newsapp.data.api.sourcesResponse.Source
import com.google.gson.annotations.SerializedName

@Parcelize
data class NewsItem(

    @field:SerializedName("publishedAt")
	val publishedAt: String? = null,

    @field:SerializedName("author")
	val author: String? = null,

    @field:SerializedName("urlToImage")
	val urlToImage: String? = null,

    @field:SerializedName("description")
	val description: String? = null,

    @field:SerializedName("source")
	val source: Source? = null,

    @field:SerializedName("title")
	val title: String? = null,

    @field:SerializedName("url")
	val url: String? = null,

    @field:SerializedName("content")
	val content: String? = null
) : Parcelable