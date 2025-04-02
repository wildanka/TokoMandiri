package com.example.tokomandiri.app.product.data

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.product.data.remote.response.ProductDto

interface HomeRepository {
    suspend fun getAllProducts() : ApiResponse<List<ProductDto>>
}
