package com.example.tokomandiri.app.common.data

import com.example.tokomandiri.app.base.data.mapper.ListMapper
import com.example.tokomandiri.app.common.data.local.entity.ProductEntity
import com.example.tokomandiri.app.common.data.network.response.ProductDto
import com.example.tokomandiri.app.common.data.network.response.Rating

object ProductDtoToEntMapper: ListMapper<ProductDto, ProductEntity> {
    override fun map(source: ProductDto): ProductEntity {
        return ProductEntity(
            id = source.id ?: 0,
            image = source.image.orEmpty(),
            price = source.price ?: 0.0,
            rating = source.rating ?: Rating(),
            description = source.description.orEmpty(),
            title = source.title.orEmpty(),
            category = source.category.orEmpty(),
        )
    }
}