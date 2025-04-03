package com.example.tokomandiri.app.cart.data.local

import com.example.tokomandiri.app.common.data.local.dao.UserCartDao
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

class UserCartLocalDataSourceImpl(private val userCartDao: UserCartDao): UserCartLocalDataSource {
    override fun getCartActiveItems(userId: Int): Flow<List<UserCartEntity>> {
        return userCartDao.getActiveUserCart()
    }
}