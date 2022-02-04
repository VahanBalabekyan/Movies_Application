package com.movies.test.shared.modules

import com.movies.test.view.activity.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { MainViewModel(get()) }
}