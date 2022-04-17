package com.stefanusj.newsapp.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.stefanusj.newsapp.data.NewsApi
import com.stefanusj.newsapp.data.NewsDatabase

class SyncWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return try {
            val response = NewsApi.retrofitService.getArticles()
            val newsDao = NewsDatabase.getDatabase(applicationContext).newsDao()
            newsDao.insertArticles(response.articles)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
