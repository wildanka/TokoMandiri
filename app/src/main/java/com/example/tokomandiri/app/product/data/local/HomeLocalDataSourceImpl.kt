package com.example.tokomandiri.app.product.data.local

import com.example.tokomandiri.app.common.data.local.dao.ProductDao
import com.example.tokomandiri.app.common.data.local.dao.UserCartDao
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

class HomeLocalDataSourceImpl(
    private val userCartDao: UserCartDao,
    private val productDao: ProductDao
) : HomeLocalDataSource {
    override fun getProductInCart(
        userId: Int,
        productId: Int
    ): Flow<UserCartEntity> {
        return userCartDao.getProductInCart(userId, productId)
    }

    override fun updateProductQtyInCart(userId: Int, productId: Int, newQty: Int) {
        return userCartDao.updateProductQtyInCart(
            productId = productId,
            userId = userId,
            qty = newQty
        )
    }

}