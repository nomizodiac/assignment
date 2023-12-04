package com.systems.assignment.products.data.repository

import com.systems.assignment.products.data.remote.ProductsApi
import com.systems.assignment.products.domain.model.ProductDetailRequest
import com.systems.assignment.products.domain.model.ProductDetailResponse
import com.systems.assignment.products.domain.model.ProductsResponse
import com.systems.assignment.products.domain.remote.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val api: ProductsApi): ProductsRepository {
    override suspend fun getProducts(): ProductsResponse {
        return api.getProducts()
    }

    override suspend fun getProductDetails(productDetailRequest: ProductDetailRequest): ProductDetailResponse {
        return api.getProductDetails(productDetailRequest.productId.toString())
    }

}