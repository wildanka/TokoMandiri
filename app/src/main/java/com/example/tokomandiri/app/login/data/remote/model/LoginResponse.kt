package com.example.tokomandiri.app.login.data.remote.model

import com.squareup.moshi.Json

data class LoginResponse(
	@Json(name="token")
	val token: String? = null
)
