package com.example.tokomandiri.app.common.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tokomandiri.app.common.data.network.response.Rating

@Entity(tableName = "userCart")
data class UserCartEntity(
    @PrimaryKey
    val productId: Int,
    val image: String,
    val price: Double,
    @Embedded
    val rating: Rating,
    val description: String,
    val title: String,
    val category: String,
    var qty: Int = 0
)