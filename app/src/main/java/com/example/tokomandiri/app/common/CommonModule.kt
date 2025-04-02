package com.example.tokomandiri.app.common

import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.tokomandiri.app.common.data.local.TokoBerdiriDatabase
import com.example.tokomandiri.app.common.data.network.FakeStoreApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val commonModule = module {
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
            .create(FakeStoreApi::class.java)
    }


    single {
        Room.databaseBuilder(
            androidApplication(),
            TokoBerdiriDatabase::class.java,
            "myGithubDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<TokoBerdiriDatabase>().productDao }
    single { get<TokoBerdiriDatabase>().userCartDao }

}