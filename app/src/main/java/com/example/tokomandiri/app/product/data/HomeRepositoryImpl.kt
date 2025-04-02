package com.example.tokomandiri.app.product.data

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.common.data.network.FakeStoreApi
import com.example.tokomandiri.app.common.data.network.response.ProductDto
import com.example.tokomandiri.app.product.data.local.HomeLocalDataSource

class HomeRepositoryImpl(private val fakeStoreApi: FakeStoreApi, private val localDataSource: HomeLocalDataSource): HomeRepository {
    override suspend fun getAllProducts(): ApiResponse<List<ProductDto>> {
        val result = fakeStoreApi.getProducts()
        return if(result.isSuccessful){
            ApiResponse.Success(result.body().orEmpty())
        }else{
            ApiResponse.Error(result.errorBody().toString())
        }
    }

    override suspend fun getProduct(id: Int): ApiResponse<ProductDto> {
        val result = fakeStoreApi.getProduct(id)

        //TODO : beware of bug possibility here, caused by type casting to "as ApiResponse<ProductDto>"
        return (if(result.isSuccessful){
            val product = result.body()
            if(product != null){
                ApiResponse.Success(result.body())
            }else{
                ApiResponse.Empty
            }
        }else{
            ApiResponse.Error(result.errorBody().toString())
        }) as ApiResponse<ProductDto>
    }
}