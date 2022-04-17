package com.stefanusj.newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stefanusj.newsapp.BaseApplication
import com.stefanusj.newsapp.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private val viewModel: ArticleViewModel by activityViewModels {
        val baseApplication = activity?.application as BaseApplication
        ArticleViewModelFactory(
            baseApplication.database.newsDao(),
            baseApplication
        )
    }

    private var _binding: HomeFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater)
        viewModel.getArticleList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArticleListAdapter { article ->
            viewModel.setArticleView(article)
            val action = HomeFragmentDirections
                .actionHomeFragmentToArticleFragment()
            findNavController().navigate(action)
        }

        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            adapter.submitList(articles)
        }

        binding.apply {
            rvArticle.adapter = adapter
        }
    }
}