package com.example.tokomandiri.app.product.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokomandiri.app.base.UiState
import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.product.data.remote.response.ProductDto
import com.example.tokomandiri.app.product.domain.HomeUseCase
import com.example.tokomandiri.framework.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: HomeUseCase
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


/*
    fun refreshList() {
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
*/

    fun fetchAllProducts() {

        job = viewModelScope.launch {
            when (val result = useCase.getAllProducts()) {
                is ApiResponse.Empty -> {
                    Log.d("WLDN", "fetchAllProducts: EMPTY")
                    _uiState.value = UiState.Success(emptyList())
                }
                is ApiResponse.Error -> {
                    _errorMessage.postValue(Event(result.errorMessage))
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
}
