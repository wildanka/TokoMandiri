package com.example.tokomandiri.app.common.data

import com.example.tokomandiri.app.base.data.mapper.ListMapper
import com.example.tokomandiri.app.common.data.local.entity.ProductEntity
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import com.example.tokomandiri.app.common.data.network.response.ProductDto
import com.example.tokomandiri.app.common.data.network.response.Rating

object ProductEntToCartMapper: ListMapper<ProductEntity, UserCartEntity> {
    override fun map(source: ProductEntity): UserCartEntity {
        return UserCartEntity(
            productId = source.id,
            image = source.image,
            price = source.price,
            rating = source.rating,
            description = source.description,
            title = source.title,
            category = source.category,
            qty = 0
        )
    }
}