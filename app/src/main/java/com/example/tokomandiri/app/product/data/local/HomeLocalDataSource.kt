package com.example.tokomandiri.app.product.data.local

import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {
    fun getProductInCart(productId: Int): Flow<UserCartEntity>
    suspend fun getProductInCartNonLive(productId: Int): UserCartEntity?
    fun updateProductQtyInCart(productId: Int, qty: Int)
    fun insertProductToCart(userCartEntity: UserCartEntity)
}