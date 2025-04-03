package com.example.tokomandiri.app.cart.domain

import androidx.paging.PagingData
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface CartUseCase {
    fun getCartActiveItems(userId: Int): Flow<List<UserCartEntity>>
    fun getPagedCartItems(): Flow<PagingData<UserCartEntity>>
    suspend fun insertProductToCart(userCartEntity: UserCartEntity)
    suspend fun removeProductFromCart(userCartEntity: UserCartEntity)

}