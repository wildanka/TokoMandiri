package com.example.tokomandiri.app.login.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.tokomandiri.app.login.data.LoginRepository
import com.example.tokomandiri.app.login.data.LoginRepositoryImpl
import com.example.tokomandiri.app.login.data.remote.FakeStoreLoginApi
import com.example.tokomandiri.app.login.domain.LoginInteractor
import com.example.tokomandiri.app.login.domain.LoginUseCase
import com.example.tokomandiri.app.login.presentation.login.LoginViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val loginModule = module {
    // Provide ChuckerInterceptor
    single {
        ChuckerInterceptor(androidApplication())
    }

    // Provide OkHttpClient with ChuckerInterceptor
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(get<ChuckerInterceptor>()) // Inject ChuckerInterceptor from Koin
            .build()
    }

    // Provide Retrofit instance
    single {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(get()) // Inject OkHttpClient from Koin
            .build()
            .create(FakeStoreLoginApi::class.java)
    }

    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }
    single<LoginUseCase>{ LoginInteractor(get()) }

    viewModel {
        LoginViewModel(get())
    }
}