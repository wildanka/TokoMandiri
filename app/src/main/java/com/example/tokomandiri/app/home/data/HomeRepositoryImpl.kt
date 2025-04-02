package com.example.tokomandiri.app.home.data

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.common.data.network.FakeStoreApi
import com.example.tokomandiri.app.home.data.remote.response.ProductDto

class HomeRepositoryImpl(private val fakeStoreApi: FakeStoreApi): HomeRepository {
    override suspend fun getAllProducts(): ApiResponse<List<ProductDto>> {
        val result = fakeStoreApi.getProducts()
        return if(result.isSuccessful){
            ApiResponse.Success(result.body().orEmpty())
        }else{
            ApiResponse.Error(result.errorBody().toString())
        }
    }
}