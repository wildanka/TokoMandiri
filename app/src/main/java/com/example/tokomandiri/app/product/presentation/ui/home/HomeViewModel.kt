package com.example.tokomandiri.app.product.presentation.ui.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokomandiri.app.base.UiState
import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.common.data.network.response.ProductDto
import com.example.tokomandiri.app.product.domain.HomeUseCase
import com.example.tokomandiri.framework.AppUtility
import com.example.tokomandiri.framework.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: HomeUseCase,
    private val context: Application
) : ViewModel() {
    private var job: Job? = null
    private val _uiState: MutableStateFlow<UiState<List<ProductDto>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ProductDto>>>
        get() = _uiState

    private val _isLoading = MutableStateFlow(false)  // Internal mutable state
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    // Store the scroll position
    var scrollPosition: Int by mutableIntStateOf(0)

    // Function to update scroll position
    fun updateScrollPosition(position: Int) {
        scrollPosition = position
    }


    fun getAllProduct(){
        _isLoading.value = true
        if (AppUtility.isNetworkAvailable(context.applicationContext)) {
            job?.cancel()
            _isLoading.value = false
            fetchAllProducts()
        } else {
            _isLoading.value = false
            _errorMessage.postValue(Event("No network connection"))
        }
    }


    fun fetchAllProducts() {
        job = viewModelScope.launch {
            when (val result = useCase.getAllProducts()) {
                is ApiResponse.Empty -> {
                    _uiState.value = UiState.Success(emptyList())
                }
                is ApiResponse.Error -> {
                    _errorMessage.postValue(Event(result.errorMessage))
                    _uiState.value = UiState.Error(result.errorMessage)
                }
                is ApiResponse.Success -> {
                    _uiState.value = UiState.Success(result.data)
                }
            }

        }
    }
}
