package com.example.tokomandiri.app.login.data.remote.model

import com.squareup.moshi.Json

data class UserDto(

	@Json(name="password")
	val password: String? = null,

	@Json(name="address")
	val address: Address? = null,

	@Json(name="phone")
	val phone: String? = null,

	@Json(name="__v")
	val v: Int? = null,

	@Json(name="name")
	val name: Name? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="email")
	val email: String? = null,

	@Json(name="username")
	val username: String? = null
)

data class Geolocation(

	@Json(name="lat")
	val lat: String? = null,

	@Json(name="long")
	val jsonMemberLong: String? = null
)

data class Name(

	@Json(name="firstname")
	val firstname: String? = null,

	@Json(name="lastname")
	val lastname: String? = null
)

data class Address(

	@Json(name="zipcode")
	val zipcode: String? = null,

	@Json(name="number")
	val number: Int? = null,

	@Json(name="city")
	val city: String? = null,

	@Json(name="street")
	val street: String? = null,

	@Json(name="geolocation")
	val geolocation: Geolocation? = null
)
