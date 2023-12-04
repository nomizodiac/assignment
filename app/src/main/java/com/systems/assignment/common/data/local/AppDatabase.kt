package com.systems.assignment.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.systems.assignment.common.data.local.dao.ProductDao
import com.systems.assignment.common.utils.Constants
import com.systems.assignment.products.domain.model.Product

@Database(
    entities = [Product::class],
    version = Constants.DATABASE_VERSION,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): ProductDao
}