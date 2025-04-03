package com.example.tokomandiri.app.cart.presentation.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tokomandiri.app.cart.domain.CartUseCase
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

class SummaryViewModel(private val cartUseCase: CartUseCase): ViewModel() {
    val pagedCartItems: Flow<PagingData<UserCartEntity>> =
        cartUseCase.getPagedCartItems().cachedIn(viewModelScope)
}