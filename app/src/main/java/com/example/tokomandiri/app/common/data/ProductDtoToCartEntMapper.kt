package com.example.tokomandiri.app.common.data

import com.example.tokomandiri.app.base.data.mapper.ListMapper
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import com.example.tokomandiri.app.common.data.network.response.ProductDto
import com.example.tokomandiri.app.common.data.network.response.Rating

object ProductDtoToCartEntMapper: ListMapper<ProductDto, UserCartEntity> {
    override fun map(source: ProductDto): UserCartEntity {
        return UserCartEntity(
            productId = source.id ?: 0,
            image = source.image.orEmpty(),
            price = source.price ?: 0.0,
            rating = source.rating ?: Rating(),
            description = source.description.orEmpty(),
            title = source.title.orEmpty(),
            category = source.category.orEmpty(),
        )
    }
}