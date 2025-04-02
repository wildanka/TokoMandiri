package com.example.tokomandiri.app.cart.data.local

import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface UserCartLocalDataSource {
    fun getCartActiveItems(userId: Int): Flow<List<UserCartEntity>>
}