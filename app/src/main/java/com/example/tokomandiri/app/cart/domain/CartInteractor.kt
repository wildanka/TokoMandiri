package com.example.tokomandiri.app.cart.domain

import androidx.paging.PagingData
import com.example.tokomandiri.app.cart.data.CartRepository
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CartInteractor(private val cartRepository: CartRepository): CartUseCase {
    override fun getCartActiveItems(userId: Int): Flow<List<UserCartEntity>> {
        return cartRepository.getCartActiveItems(userId)
    }
    override fun getPagedCartItems(): Flow<PagingData<UserCartEntity>> {
        return cartRepository.getPagedCartItems()
    }

    override suspend fun insertProductToCart(userCartEntity: UserCartEntity) {
        return withContext(Dispatchers.IO) { cartRepository.insertProductToCart(userCartEntity) }
    }

    override suspend fun removeProductFromCart(userCartEntity: UserCartEntity) {
        return withContext(Dispatchers.IO) { cartRepository.removeProductFromCart(userCartEntity) }
    }
}