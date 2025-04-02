package com.example.tokomandiri.app.product.di

import com.example.tokomandiri.app.common.data.local.TokoBerdiriDatabase
import com.example.tokomandiri.app.product.data.HomeRepository
import com.example.tokomandiri.app.product.data.HomeRepositoryImpl
import com.example.tokomandiri.app.product.data.local.HomeLocalDataSource
import com.example.tokomandiri.app.product.data.local.HomeLocalDataSourceImpl
import com.example.tokomandiri.app.product.domain.HomeInteractor
import com.example.tokomandiri.app.product.domain.HomeUseCase
import com.example.tokomandiri.app.product.presentation.ui.detail.DetailViewModel
import com.example.tokomandiri.app.product.presentation.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single { get<TokoBerdiriDatabase>().productDao }
    single { get<TokoBerdiriDatabase>().userCartDao }

    single<HomeLocalDataSource>{
        HomeLocalDataSourceImpl(get(), get())
    }

    single<HomeRepository>{
        HomeRepositoryImpl(get(), get())
    }

    single<HomeUseCase>{
        HomeInteractor(get())
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        DetailViewModel(get())
    }
}