package com.example.newsapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.newsResponse.NewsItem
import com.example.newsapp.data.api.newsResponse.NewsResponse
import com.example.newsapp.data.api.sourcesResponse.Source
import com.example.newsapp.data.api.sourcesResponse.SourceResponse
import com.example.newsapp.data.dataSourceImp.newsDataSourcesImpl.NewsOnlineDataSourcesImpl
import com.example.newsapp.data.dataSourceImp.sourcesDataSourcesImpl.SourcesOnlineDataSourceImpl
import com.example.newsapp.data.repositoryImpl.sorucrsRepoImpl.SourcesRepositoryImpl
import com.example.newsapp.data.repositoryImpl.newsSourcesRepoImpl.NewsSourcesRepositoryImpl
import com.example.newsapp.dataSource.newsDataSource.NewsDataSource
import com.example.newsapp.dataSource.sourcesDataSource.SourcesDataSource
import com.example.newsapp.repository.newsRepository.NewsSourcesRepository
import com.example.newsapp.repository.sourcesRepository.SourceRepository
import com.example.newsapp.ui.ViewError
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val sourceRepository: SourceRepository ,
    private val newsSourcesRepository: NewsSourcesRepository
) : ViewModel() {
    val shouldShowLoading = MutableLiveData<Boolean>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()
    val newsLiveData = MutableLiveData<List<NewsItem?>?>()
    val errorLiveData = MutableLiveData<ViewError>()



    fun getNewsSources() {
        shouldShowLoading.postValue(true)
        viewModelScope.launch {

            try {
                val sources = sourceRepository.getSources()
                sourcesLiveData.postValue(sources)
            } catch (e: HttpException) {
                // handel server exception
                val errorBodyJsonString = e.response()?.errorBody()?.string()
                val response = Gson().fromJson(errorBodyJsonString, SourceResponse::class.java)
                errorLiveData.postValue(
                    ViewError(message = response.message) {
                        getNewsSources()
                    })

            } catch (e: Exception) {
                // handel client exception
                errorLiveData.postValue(
                    ViewError(throwable = e) {
                        getNewsSources()
                    }
                )

            } finally {
                shouldShowLoading.postValue(false)
            }
        }
    }

    fun getNews(sourceId: String?, pageSize: Int, page: Int) {
        shouldShowLoading.postValue(true)
        viewModelScope.launch {
            try {
               val response = newsSourcesRepository.getNews(sourceId=sourceId )
                newsLiveData.postValue(response)
            } catch (e: HttpException) {
                val errorBodyJsonString = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBodyJsonString, NewsResponse::class.java)
                errorLiveData.postValue(
                    ViewError(message = errorResponse.message) {
                        getNews(sourceId, pageSize, page)
                    }
                )
            } catch (e: Exception) {
                errorLiveData.postValue(
                    ViewError(
                        throwable = e
                    ) {
                        getNews(sourceId, pageSize, page)
                    }
                )

            } finally {
                shouldShowLoading.postValue(false)
            }
        }
    }
}