package com.stefanusj.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.stefanusj.newsapp.BaseApplication
import com.stefanusj.newsapp.databinding.ArticleFragmentBinding

class ArticleFragment : Fragment() {

    private val viewModel: ArticleViewModel by activityViewModels {
        val baseApplication = activity?.application as BaseApplication
        ArticleViewModelFactory(
            baseApplication.database.newsDao(),
            baseApplication
        )
    }

    private var _binding: ArticleFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArticleFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.article.observe(viewLifecycleOwner) { article ->
            binding.wvArticle.loadUrl(article.url)
        }
    }

}