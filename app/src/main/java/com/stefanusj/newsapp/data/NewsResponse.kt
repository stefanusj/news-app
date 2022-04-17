package com.stefanusj.newsapp.data

data class NewsResponse(
    val status: String,
    val totalResults: Long,
    val articles: List<Article>
)
