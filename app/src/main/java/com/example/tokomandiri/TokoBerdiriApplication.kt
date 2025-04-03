package com.example.tokomandiri

import android.app.Application
import com.example.tokomandiri.app.cart.di.cartModule
import com.example.tokomandiri.app.common.commonModule
import com.example.tokomandiri.app.product.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TokoBerdiriApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TokoBerdiriApplication)
            modules(commonModule, homeModule, cartModule)
        }
    }
}