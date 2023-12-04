package com.systems.assignment.products.data.remote

import com.systems.assignment.products.domain.model.ProductDetailResponse
import com.systems.assignment.products.domain.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    @GET("cart/list")
    suspend fun getProducts(): ProductsResponse

    @GET("cart/{product_id}/detail")
    suspend fun getProductDetails(@Path(value = "product_id", encoded = true) productId: String): ProductDetailResponse
}