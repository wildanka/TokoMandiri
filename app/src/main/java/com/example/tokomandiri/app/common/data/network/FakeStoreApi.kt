package com.example.tokomandiri.app.common.data.network

import com.example.tokomandiri.app.product.data.remote.response.ProductDto
import com.example.tokomandiri.app.login.data.LoginResponse
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

    @POST("login")
    suspend fun login(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): Response<LoginResponse>
}