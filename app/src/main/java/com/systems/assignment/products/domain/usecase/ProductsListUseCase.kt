package com.systems.assignment.products.domain.usecase

import com.systems.assignment.common.data.remote.ApiErrorHandle
import com.systems.assignment.common.domain.model.BaseRequest
import com.systems.assignment.common.domain.repository.DatabaseRepository
import com.systems.assignment.common.domain.usecase.ApiBaseUseCase
import com.systems.assignment.products.domain.model.ProductsResponse
import com.systems.assignment.products.domain.remote.ProductsRepository
import javax.inject.Inject

class ProductsListUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val databaseRepository: DatabaseRepository,
    apiErrorHandler: ApiErrorHandle,
) : ApiBaseUseCase<ProductsResponse, BaseRequest>(apiErrorHandler) {

    override suspend fun run(params: BaseRequest): ProductsResponse {
        return productsRepository.getProducts()
    }
}