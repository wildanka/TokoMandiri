package com.example.tokomandiri.app.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tokomandiri.app.base.UiState
import com.example.tokomandiri.app.cart.domain.CartUseCase
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val cartUseCase: CartUseCase): ViewModel() {
    private var coroutineJob: Job? = null
    private val _uiState: MutableStateFlow<UiState<List<UserCartEntity>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<UserCartEntity>>>
        get() = _uiState

    val pagedCartItems: Flow<PagingData<UserCartEntity>> =
        cartUseCase.getPagedCartItems().cachedIn(viewModelScope)

    fun insertProductToCart(userCartEntity: UserCartEntity){
        viewModelScope.launch {
            cartUseCase.insertProductToCart(userCartEntity)
        }
    }

    fun removeProductFromCart(userCartEntity: UserCartEntity){
        viewModelScope.launch {
            cartUseCase.removeProductFromCart(userCartEntity)
        }
    }
}