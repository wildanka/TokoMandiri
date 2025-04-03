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
        productId: Int
    ): Flow<UserCartEntity> {
        return userCartDao.getProductInCart(productId)
    }

    override suspend fun getProductInCartNonLive(productId: Int): UserCartEntity? {
        return userCartDao.getProductInCartNonLive(productId)
    }

    override fun updateProductQtyInCart(productId: Int, qty: Int) {
        return userCartDao.updateProductQtyInCart(
            productId = productId,
            qty = qty
        )
    }

    override fun insertProductToCart(userCartEntity: UserCartEntity) {
        return userCartDao.insert(userCartEntity)
    }
}