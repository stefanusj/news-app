package com.stefanusj.newsapp

import android.app.Application
import com.stefanusj.newsapp.data.NewsDatabase

class BaseApplication : Application() {

    val database: NewsDatabase by lazy { NewsDatabase.getDatabase(this) }

}