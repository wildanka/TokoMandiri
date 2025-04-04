package com.example.tokomandiri.app.common.data.network

import com.example.tokomandiri.app.common.data.network.response.ProductDto
import com.example.tokomandiri.app.login.data.remote.model.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FakeStoreApi {

    @GET("products")
    suspend fun getProducts(): Response<List<ProductDto>>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") productId: Int): Response<ProductDto>
}