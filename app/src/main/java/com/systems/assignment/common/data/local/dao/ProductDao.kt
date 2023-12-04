package com.systems.assignment.common.data.local.dao

import androidx.room.*
import com.systems.assignment.products.domain.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product LIMIT 3")
    suspend fun getProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: Product): Long

    @Query("DELETE FROM product")
    suspend fun deleteAll(): Int
}