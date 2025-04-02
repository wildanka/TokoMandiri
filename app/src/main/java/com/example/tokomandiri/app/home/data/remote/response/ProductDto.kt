package com.example.tokomandiri.app.home.data.remote.response

import com.squareup.moshi.Json

data class ProductDto(

    @Json(name="image")
    val image: String? = null,

    @Json(name="price")
    val price: Double? = null,

    @Json(name="rating")
    val rating: Rating? = null,

    @Json(name="description")
    val description: String? = null,

    @Json(name="id")
    val id: Int = 0,

    @Json(name="title")
    val title: String? = null,

    @Json(name="category")
    val category: String? = null
)

data class Rating(
    @Json(name="rate")
    val rate: Double? = null,

    @Json(name="count")
    val count: Int? = null
)