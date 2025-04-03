package com.example.tokomandiri.app.cart.data

import androidx.paging.PagingData
import com.example.tokomandiri.app.cart.data.local.UserCartLocalDataSource
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow


class CartRepositoryImpl(private val userCartLocalDataSource: UserCartLocalDataSource) : CartRepository {
    override fun getCartActiveItems(userId: Int): Flow<List<UserCartEntity>> {
        return userCartLocalDataSource.getCartActiveItems(userId)
    }

    override fun getPagedCartItems(): Flow<PagingData<UserCartEntity>> {
        return userCartLocalDataSource.getPagedCartItems()
    }

    override suspend fun insertProductToCart(userCartEntity: UserCartEntity){
        return userCartLocalDataSource.insertProductToCart(userCartEntity)
    }

    override suspend fun removeProductFromCart(userCartEntity: UserCartEntity) {
        return userCartLocalDataSource.removeProductFromCart(userCartEntity)
    }
}