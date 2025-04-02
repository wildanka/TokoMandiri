package com.example.tokomandiri.app.login.data

import com.squareup.moshi.Json

data class LoginResponse(
	@Json(name="token")
	val token: String? = null
)
