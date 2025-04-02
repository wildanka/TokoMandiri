package com.example.tokomandiri.app.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tokomandiri.app.common.data.local.dao.ProductDao
import com.example.tokomandiri.app.common.data.local.dao.UserCartDao
import com.example.tokomandiri.app.common.data.local.entity.ProductEntity
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity

@Database(
    entities = [
        ProductEntity::class,
        UserCartEntity::class
    ],
    version = 3,

    )
abstract class TokoBerdiriDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
    abstract val userCartDao: UserCartDao
}