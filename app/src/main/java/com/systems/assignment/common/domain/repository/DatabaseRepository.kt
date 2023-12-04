package com.systems.assignment.common.domain.repository

import com.systems.assignment.products.domain.model.Product

interface DatabaseRepository {

    suspend fun getProducts(): List<Product>

    suspend fun saveProducts(list: List<Product>)

    suspend fun clearDatabase()
}