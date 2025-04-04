package com.example.tokomandiri.app.login.domain

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.login.data.remote.model.UserLoginDto

interface LoginUseCase {
    suspend fun login(username: String, password: String) : ApiResponse<UserLoginDto?>
}