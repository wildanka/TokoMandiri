package com.example.tokomandiri.app.cart.data

import com.example.tokomandiri.app.cart.data.local.UserCartLocalDataSource
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow


class CartRepositoryImpl(private val userCartLocalDataSource: UserCartLocalDataSource) : CartRepository {
    override fun getCartActiveItems(userId: Int): Flow<List<UserCartEntity>> {
        return userCartLocalDataSource.getCartActiveItems(userId)
    }
}