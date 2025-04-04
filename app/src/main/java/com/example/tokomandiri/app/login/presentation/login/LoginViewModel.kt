package com.example.tokomandiri.app.login.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.login.data.remote.model.UserLoginDto
import com.example.tokomandiri.app.login.domain.LoginUseCase
import com.example.tokomandiri.app.login.domain.model.LoginUiState
import com.example.tokomandiri.framework.Event
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: LoginUseCase) : ViewModel() {
    private var _errorMessage = MutableStateFlow<Event<String>?>(null)
    val errorMessage: StateFlow<Event<String>?> = _errorMessage.asStateFlow()

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState: StateFlow<LoginUiState> = _loginState.asStateFlow()

    private val _loginEvent = MutableSharedFlow<UserLoginDto?>()
    val loginEvent: SharedFlow<UserLoginDto?> = _loginEvent.asSharedFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = useCase.login(username, password)
            when (result) {
                is ApiResponse.Error -> {
                    _errorMessage.value = Event(result.errorMessage.toString())
                }

                is ApiResponse.Success<UserLoginDto?> -> {
                    //TODO : trigger my Login Screen to do something
                    _loginState.value = LoginUiState.Success
                    _loginEvent.emit(result.data)
                }

                else -> {
                    _errorMessage.value = Event("unknown API response type: ${result::class.simpleName} | ${result::class.qualifiedName}")
                }
            }
        }
    }
}