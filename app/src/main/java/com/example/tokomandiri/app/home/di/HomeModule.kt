package com.example.tokomandiri.app.home.di

import com.example.tokomandiri.app.home.data.HomeRepository
import com.example.tokomandiri.app.home.data.HomeRepositoryImpl
import com.example.tokomandiri.app.home.domain.HomeInteractor
import com.example.tokomandiri.app.home.domain.HomeUseCase
import com.example.tokomandiri.app.home.presentation.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single<HomeRepository>{
        HomeRepositoryImpl(get())
    }
    single<HomeRepository>{
        HomeRepositoryImpl(get())
    }

    single<HomeUseCase>{
        HomeInteractor(get())
    }

    viewModel {
        HomeViewModel(get())
    }
}