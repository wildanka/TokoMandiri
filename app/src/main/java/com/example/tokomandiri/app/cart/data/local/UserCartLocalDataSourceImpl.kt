package com.example.tokomandiri.app.cart.data.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tokomandiri.app.common.data.local.dao.UserCartDao
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

class UserCartLocalDataSourceImpl(private val userCartDao: UserCartDao): UserCartLocalDataSource {
    override fun getCartActiveItems(userId: Int): Flow<List<UserCartEntity>> {
        return userCartDao.getActiveUserCart()
    }

    override fun getPagedCartItems(): Flow<PagingData<UserCartEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, // Define the page size
                enablePlaceholders = false
            ),
            pagingSourceFactory = { userCartDao.pagingSource() }
        ).flow
    }

    override fun insertProductToCart(userCartEntity: UserCartEntity) {
        return userCartDao.insert(userCartEntity)
    }
    override fun removeProductFromCart(userCartEntity: UserCartEntity) {
        return userCartDao.delete(userCartEntity)
    }
}