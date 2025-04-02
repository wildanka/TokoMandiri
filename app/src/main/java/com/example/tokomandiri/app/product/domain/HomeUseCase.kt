package com.example.tokomandiri.app.product.domain

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.product.data.remote.response.ProductDto

interface HomeUseCase {
    suspend fun getAllProducts() : ApiResponse<List<ProductDto>>
    suspend fun getProduct(id: Int) : ApiResponse<ProductDto>
}