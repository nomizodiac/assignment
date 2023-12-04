package com.systems.assignment.common.data.local

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import com.systems.assignment.common.data.repositoryimp.DatabaseRepositoryImp
import com.systems.assignment.common.domain.repository.DatabaseRepository
import com.systems.assignment.common.utils.Constants
import com.systems.assignment.products.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

class DatabaseManager @Inject internal constructor(private val context: Context?) {

    private fun getAppDatabase(mContext: Context?): AppDatabase {
        return Room.databaseBuilder(
            mContext!!,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    private lateinit var databaseRepository: DatabaseRepository

    private fun getDatabaseRepository(): DatabaseRepository {
        if (!::databaseRepository.isInitialized) {
            val appDb = getAppDatabase(context)
            databaseRepository = DatabaseRepositoryImp(appDb.accountDao())
        }
        return databaseRepository
    }

    fun getProducts(): List<Product> = runBlocking(context = Dispatchers.IO) {
        return@runBlocking getDatabaseRepository().getProducts()
    }
}