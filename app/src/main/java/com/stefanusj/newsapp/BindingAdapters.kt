package com.stefanusj.newsapp

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("src")
fun ImageView.bindStatus(url: String?) {
    if (url == null) {
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
        Glide.with(context).load(url).into(this)
    }
}
