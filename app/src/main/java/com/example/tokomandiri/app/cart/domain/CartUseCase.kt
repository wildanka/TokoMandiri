package com.example.tokomandiri.app.cart.domain

import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface CartUseCase {
    fun getCartActiveItems(userId: Int): Flow<List<UserCartEntity>>
}