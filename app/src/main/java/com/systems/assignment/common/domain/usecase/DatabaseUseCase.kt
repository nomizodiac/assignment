package com.systems.assignment.common.domain.usecase

import com.systems.assignment.common.domain.repository.DatabaseRepository
import com.systems.assignment.products.domain.model.Product
import javax.inject.Inject

class DatabaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    suspend fun getProducts(): List<Product> {
        return databaseRepository.getProducts()
    }

    suspend fun saveProducts(list: List<Product>) {
        return databaseRepository.saveProducts(list)
    }

}