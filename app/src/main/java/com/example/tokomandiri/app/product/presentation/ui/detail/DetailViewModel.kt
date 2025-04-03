package com.example.tokomandiri.app.product.presentation.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokomandiri.app.base.UiState
import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import com.example.tokomandiri.app.product.domain.HomeUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val useCase: HomeUseCase,
    private val context: Application
) : ViewModel(){
    private var job: Job? = null
    private val _uiState: MutableStateFlow<UiState<UserCartEntity>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<UserCartEntity>>
        get() = _uiState


    fun fetchProduct(id: Int) {
        job = viewModelScope.launch {
            when (val result = useCase.getProduct(id)) {
                is ApiResponse.Empty -> {
                    _uiState.value = UiState.Error("Something went wrong")
                }
                is ApiResponse.Error -> {
                    _uiState.value = UiState.Error(result.errorMessage)
                }
                is ApiResponse.Success -> {
                    _uiState.value = UiState.Success(result.data)
                }
            }

        }
    }

    fun addProductToCart(userCartEntity: UserCartEntity, qty: Int){
        viewModelScope.launch {
            useCase.insertProductToCart(userCartEntity.copy(qty = qty))
        }
    }
}