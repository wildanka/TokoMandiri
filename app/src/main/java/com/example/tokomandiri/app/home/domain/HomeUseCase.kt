package com.example.tokomandiri.app.home.domain

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.home.data.remote.response.ProductDto

interface HomeUseCase {
    suspend fun getAllProducts() : ApiResponse<List<ProductDto>>
}