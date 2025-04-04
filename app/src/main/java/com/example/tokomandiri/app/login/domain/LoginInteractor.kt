package com.example.tokomandiri.app.login.domain

import com.example.tokomandiri.app.base.data.ApiResponse
import com.example.tokomandiri.app.login.data.LoginRepository
import com.example.tokomandiri.app.login.data.remote.model.UserLoginDto

class LoginInteractor(private val repository: LoginRepository) : LoginUseCase {
    override suspend fun login(
        username: String,
        password: String
    ): ApiResponse<UserLoginDto?> {
        return repository.login(username, password)
    }
}