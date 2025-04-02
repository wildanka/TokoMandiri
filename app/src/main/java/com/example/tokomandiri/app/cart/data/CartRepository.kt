package com.example.tokomandiri.app.cart.data

import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartActiveItems(userId: Int): Flow<List<UserCartEntity>>
}