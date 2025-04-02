package com.example.tokomandiri.app.product.domain

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.product.data.HomeRepository
import com.example.tokomandiri.app.product.data.remote.response.ProductDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeInteractor(private val homeRepository: HomeRepository) : HomeUseCase {
    override suspend fun getAllProducts(): ApiResponse<List<ProductDto>> {
        return withContext(Dispatchers.IO){
            homeRepository.getAllProducts()
        }
    }
}