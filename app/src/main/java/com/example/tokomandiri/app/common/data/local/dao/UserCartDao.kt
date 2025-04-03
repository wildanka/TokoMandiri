package com.example.tokomandiri.app.common.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tokomandiri.app.common.data.local.entity.UserCartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCartDao {

    @Query("SELECT * FROM `userCart` WHERE qty>0")
    fun getActiveUserCart(): Flow<List<UserCartEntity>>

    @Query("SELECT * FROM `userCart` WHERE qty>0")
    fun pagingSource(): PagingSource<Int, UserCartEntity>


    @Query("SELECT * FROM `userCart` WHERE productId=:productId")
    fun getProductInCart(productId: Int): Flow<UserCartEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(messages: UserCartEntity)

    @Delete
    fun delete(messages: UserCartEntity)


    @Query("UPDATE userCart SET qty=:qty WHERE productId=:productId")
    fun updateProductQtyInCart(productId: Int, qty: Int)

}