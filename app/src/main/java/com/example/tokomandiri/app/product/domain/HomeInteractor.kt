package com.example.tokomandiri.app.product.domain

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.common.data.local.entity.ProductEntity
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import com.example.tokomandiri.app.product.data.HomeRepository
import com.example.tokomandiri.app.common.data.network.response.ProductDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeInteractor(private val homeRepository: HomeRepository) : HomeUseCase {
    override suspend fun getAllProducts(): ApiResponse<List<ProductDto>> {
        return withContext(Dispatchers.IO){
            homeRepository.getAllProducts()
        }
    }

    override suspend fun getProduct(id: Int): ApiResponse<ProductEntity> {
        return withContext(Dispatchers.IO){
            homeRepository.getProduct(id)
        }
    }

    override suspend fun updateProductCartQty(
        productId: Int,
        qty: Int
    ) {
        return withContext(Dispatchers.IO){
            homeRepository.updateProductCartQty(productId, qty)
        }
    }

    override suspend fun insertProductToCart(userCartEntity: UserCartEntity) {
        return withContext(Dispatchers.IO){
            homeRepository.insertProductToCart(userCartEntity)
        }
    }
}