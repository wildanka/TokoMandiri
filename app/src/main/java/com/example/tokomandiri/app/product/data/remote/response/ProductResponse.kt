package com.example.tokomandiri.app.product.data.remote.response

import com.squareup.moshi.Json

data class ProductResponse(

	@Json(name="ProductResponse")
	val productResponse: List<ProductDto?>? = null
)



