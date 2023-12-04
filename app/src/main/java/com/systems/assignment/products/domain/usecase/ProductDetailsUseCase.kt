package com.systems.assignment.products.domain.usecase

import com.systems.assignment.common.data.remote.ApiErrorHandle
import com.systems.assignment.common.domain.usecase.ApiBaseUseCase
import com.systems.assignment.products.domain.model.ProductDetailRequest
import com.systems.assignment.products.domain.model.ProductDetailResponse
import com.systems.assignment.products.domain.remote.ProductsRepository
import javax.inject.Inject

class ProductDetailsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    apiErrorHandler: ApiErrorHandle,
) : ApiBaseUseCase<ProductDetailResponse, ProductDetailRequest>(apiErrorHandler) {
    
    override suspend fun run(params: ProductDetailRequest): ProductDetailResponse {
        return productsRepository.getProductDetails(params)
    }
}