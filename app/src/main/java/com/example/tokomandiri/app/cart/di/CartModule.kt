package com.example.tokomandiri.app.cart.di

import com.example.tokomandiri.app.cart.data.CartRepository
import com.example.tokomandiri.app.cart.data.CartRepositoryImpl
import com.example.tokomandiri.app.cart.data.local.UserCartLocalDataSource
import com.example.tokomandiri.app.cart.data.local.UserCartLocalDataSourceImpl
import com.example.tokomandiri.app.cart.domain.CartInteractor
import com.example.tokomandiri.app.cart.domain.CartUseCase
import com.example.tokomandiri.app.cart.presentation.CartViewModel
import com.example.tokomandiri.app.common.data.local.TokoBerdiriDatabase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val cartModule = module {
    single { get<TokoBerdiriDatabase>().userCartDao }
    single<UserCartLocalDataSource> {
        UserCartLocalDataSourceImpl(get())
    }
    single<CartRepository> {
        CartRepositoryImpl(get())
    }
    single<CartUseCase>{ CartInteractor(get()) }

    viewModel {
        CartViewModel(get())
    }
}