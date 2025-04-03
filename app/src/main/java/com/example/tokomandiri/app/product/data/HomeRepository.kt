package com.example.tokomandiri.app.product.data

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.common.data.local.entity.ProductEntity
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import com.example.tokomandiri.app.common.data.network.response.ProductDto

interface HomeRepository {
    suspend fun getAllProducts() : ApiResponse<List<ProductDto>>
    suspend fun getProduct(id: Int): ApiResponse<UserCartEntity>
    suspend fun updateProductCartQty(productId: Int, qty: Int)
    suspend fun insertProductToCart(userCartEntity: UserCartEntity)


}
