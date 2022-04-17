package com.stefanusj.newsapp.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.stefanusj.newsapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://newsapi.org/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {

    @GET("top-headlines?country=id&apiKey=${BuildConfig.NEWS_API_KEY}")
    suspend fun getArticles(): NewsResponse
}

object NewsApi {

    val retrofitService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}