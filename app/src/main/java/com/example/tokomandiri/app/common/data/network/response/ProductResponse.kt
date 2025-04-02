package com.example.tokomandiri.app.common.data.network.response

import com.squareup.moshi.Json

data class ProductResponse(

	@Json(name="ProductResponse")
	val productResponse: List<ProductDto?>? = null
)



