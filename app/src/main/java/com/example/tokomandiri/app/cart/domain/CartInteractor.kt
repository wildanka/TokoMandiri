package com.example.tokomandiri.app.cart.domain

import com.example.tokomandiri.app.cart.data.CartRepository
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

class CartInteractor(private val cartRepository: CartRepository): CartUseCase {
    override fun getCartActiveItems(userId: Int): Flow<List<UserCartEntity>> {
        return cartRepository.getCartActiveItems(userId)
    }
}