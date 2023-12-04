package com.systems.assignment.common.data.repositoryimp

import com.systems.assignment.common.data.local.dao.ProductDao
import com.systems.assignment.common.domain.repository.DatabaseRepository
import com.systems.assignment.products.domain.model.Product

class DatabaseRepositoryImp(private val productDao: ProductDao) : DatabaseRepository {

    override suspend fun getProducts(): List<Product> {
        return productDao.getProducts()
    }

    override suspend fun saveProducts(list: List<Product>) {
        list.forEach {
            productDao.saveProduct(it)
        }
    }

    override suspend fun clearDatabase() {
        productDao.deleteAll()
    }
}
