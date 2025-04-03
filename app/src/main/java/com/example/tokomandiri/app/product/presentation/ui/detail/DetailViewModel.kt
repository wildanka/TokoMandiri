package com.example.tokomandiri.app.product.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokomandiri.app.base.UiState
import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.common.data.local.entity.ProductEntity
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import com.example.tokomandiri.app.product.domain.HomeUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val useCase: HomeUseCase
) : ViewModel(){
    private var job: Job? = null
    private val _uiState: MutableStateFlow<UiState<ProductEntity>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<ProductEntity>>
        get() = _uiState


    fun fetchProduct(id: Int) {
        job = viewModelScope.launch {
            when (val result = useCase.getProduct(id)) {
                is ApiResponse.Empty -> {
                    Log.d("WLDN", "fetchAllProducts: EMPTY")
                    _uiState.value = UiState.Error("Something went wrong")
                }
                is ApiResponse.Error -> {
//                    _errorMessage.postValue(Event(result.errorMessage))
                    _uiState.value = UiState.Error(result.errorMessage)
                    Log.d("WLDN", "fetchAllProducts: ERROR")
                }
                is ApiResponse.Success -> {
                    _uiState.value = UiState.Success(result.data)
                    Log.d("WLDN", "fetchAllProducts: SUCCESS")
                }
            }

        }
    }

    fun addProductToCart(productEntity: ProductEntity, qty: Int){
        val userCartEntity = UserCartEntity(
            productId = productEntity.id,
            image = productEntity.image,
            price = productEntity.price,
            rating = productEntity.rating,
            description = productEntity.description,
            title = productEntity.title,
            category = productEntity.category,
            qty = qty
        )
        viewModelScope.launch {
            useCase.insertProductToCart(userCartEntity)
        }
    }
}