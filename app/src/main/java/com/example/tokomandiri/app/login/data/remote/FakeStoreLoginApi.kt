package com.example.tokomandiri.app.login.data.remote

import com.example.tokomandiri.app.login.data.remote.model.LoginRequest
import com.example.tokomandiri.app.login.data.remote.model.LoginResponse
import com.example.tokomandiri.app.login.data.remote.model.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface FakeStoreLoginApi {

    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}