package com.example.newsapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.data.api.newsResponse.NewsItem
import com.example.newsapp.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initPrams()


    }

    lateinit var newsItem: NewsItem
    private fun initPrams() {
        newsItem = ((intent.getParcelableExtra("news")as? NewsItem)!!)
        viewBinding.news = newsItem

    }


}