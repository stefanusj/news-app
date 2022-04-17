package com.stefanusj.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stefanusj.newsapp.data.Article
import com.stefanusj.newsapp.databinding.ArticleItemBinding

class ArticleListAdapter(
    private val onArticleClicked: (Article) -> Unit
) : ListAdapter<Article, ArticleListAdapter.ArticleViewHolder>(DiffCallback) {

    class ArticleViewHolder(
        private val binding: ArticleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            article: Article,
            onArticleClicked: (Article) -> Unit
        ) = binding.apply {
            this.article = article
            this.parentLayout.setOnClickListener { onArticleClicked(article) }
            executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(
            ArticleItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article, onArticleClicked)
    }
}
