package com.example.newsapp.ui

import com.example.newsapp.ui.news.NewsFragment

data class ViewError(
    val message : String?=null,
    val throwable: Throwable?=null,
    val onTryAgainClickListener: OnTryAgainClickListener?=null
)

fun interface OnTryAgainClickListener {
    fun onTryAgainClick()
}
