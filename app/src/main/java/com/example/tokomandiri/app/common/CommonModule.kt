package com.example.tokomandiri.app.common

import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
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
            "TokoBerdiriDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<TokoBerdiriDatabase>().productDao }
    single { get<TokoBerdiriDatabase>().userCartDao }


//    single { EncryptedPreferencesProvider(androidApplication()) }
//    single {
//        get<EncryptedPreferencesProvider>().createEncryptedPreferences()
//    }

    single<SharedPreferences> {
        // Your existing code to create EncryptedSharedPreferences
        val KEY_SIZE = 256
        val KEY_ALIAS = "_androidx_security_master_key_"
        val appPreferenceName = "WILDAN_MANDIRI_PREF"
        val spec = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(KEY_SIZE)
            .build()

        val masterKey = MasterKey.Builder(androidApplication())
            .setKeyGenParameterSpec(spec)
            .build()

        EncryptedSharedPreferences.create(
            androidApplication(),
            appPreferenceName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }


}