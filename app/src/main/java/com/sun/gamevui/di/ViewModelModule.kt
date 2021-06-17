package com.sun.gamevui.di

import com.sun.gamevui.MainViewModel
import com.sun.gamevui.ui.detail.GameDetailViewModel
import com.sun.gamevui.ui.home.HomeViewModel
import com.sun.gamevui.ui.news.NewsViewModel
import com.sun.gamevui.ui.saved.SavedViewModel
import com.sun.gamevui.ui.seach.SearchViewModel
import com.sun.gamevui.ui.tag.TagViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { GameDetailViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { SavedViewModel(get()) }
    viewModel { TagViewModel(get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { MainViewModel() }
}
