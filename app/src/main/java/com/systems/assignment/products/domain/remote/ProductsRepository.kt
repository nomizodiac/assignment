package com.systems.assignment.products.domain.remote

import com.systems.assignment.products.domain.model.ProductsResponse
import com.systems.assignment.products.domain.model.ProductDetailResponse
import com.systems.assignment.products.domain.model.ProductDetailRequest

interface ProductsRepository {

    suspend fun getProducts(): ProductsResponse

    suspend fun getProductDetails(productDetailRequest: ProductDetailRequest): ProductDetailResponse
}