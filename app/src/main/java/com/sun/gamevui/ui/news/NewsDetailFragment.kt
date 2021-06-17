package com.sun.gamevui.ui.news

import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sun.gamevui.R
import com.sun.gamevui.base.BaseFragment
import com.sun.gamevui.base.BaseViewModel
import com.sun.gamevui.databinding.FragmentNewsDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {

    private val arg: NewsDetailFragmentArgs by navArgs()

    override val layoutId get() = R.layout.fragment_news_detail
    override val viewModel by viewModel<NewsViewModel>()

    override fun initViews() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            webNews.apply {
                webViewClient = WebViewClient()
                loadUrl(arg.link)
            }
        }
    }

    override fun initData() {
    }

    override fun initActions() {
        binding?.imageBack?.setOnClickListener {
            findNavController().popBackStack()
        }
        onBackPressed()
        refreshWebView()
    }

    private fun onBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding?.webNews?.canGoBack() == true) {
                    binding?.webNews?.goBack()
                } else {
                    findNavController().popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this,callback)
    }

    private fun refreshWebView() {
        binding?.apply {
            refreshLayout.setOnRefreshListener {
                webNews.reload()
                refreshLayout.isRefreshing = false
            }
        }
    }
}
