package com.example.tokomandiri.app.common.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCartDao {

    @Query("SELECT * FROM `userCart` WHERE qty>0 AND userId=:userId")
    fun getActiveUserCart(userId: Int): Flow<List<UserCartEntity>>


    @Query("SELECT * FROM `userCart` WHERE productId=:productId AND userId=:userId")
    fun getProductInCart(userId: Int, productId: Int): Flow<UserCartEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(messages: UserCartEntity)


    @Query("UPDATE userCart SET qty=:qty WHERE productId=:productId AND userId=:userId")
    fun updateProductQtyInCart(productId: Int, userId: Int, qty: Int)

}