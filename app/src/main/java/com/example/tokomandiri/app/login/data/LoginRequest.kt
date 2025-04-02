package com.example.tokomandiri.app.login.data

import com.squareup.moshi.Json

data class LoginRequest(

	@Json(name="password")
	val password: String? = null,

	@Json(name="username")
	val username: String? = null
)
