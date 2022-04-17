package com.stefanusj.newsapp.ui

import androidx.lifecycle.*
import androidx.work.*
import com.stefanusj.newsapp.*
import com.stefanusj.newsapp.data.Article
import com.stefanusj.newsapp.data.NewsDao
import com.stefanusj.newsapp.worker.SyncWorker
import java.util.concurrent.TimeUnit

class ArticleViewModel(
    newsDao: NewsDao,
    private val application: BaseApplication
) : AndroidViewModel(application) {

    val articles = newsDao.getArticles().asLiveData()

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    fun getArticleList() {
        val workRequest = OneTimeWorkRequest.from(SyncWorker::class.java)

        WorkManager.getInstance(application).enqueue(workRequest)
    }

    fun setArticleView(article: Article) {
        _article.value = article
    }
}

class ArticleViewModelFactory(
    private val newsDao: NewsDao,
    private val application: BaseApplication
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArticleViewModel(newsDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}