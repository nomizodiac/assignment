package com.systems.assignment.viewmodels

import com.systems.assignment.common.data.remote.Outcome
import com.systems.assignment.products.domain.model.ProductDetailResponse
import com.systems.assignment.products.domain.usecase.ProductDetailsUseCase
import com.systems.assignment.products.presentation.details.ProductDetailsViewModel
import com.systems.assignment.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.ArgumentMatchers.any

class ProductDetailsViewModelTest : BaseUnitTest() {

    private val productDetailsUseCase: ProductDetailsUseCase = mockk()
    private var viewModel = ProductDetailsViewModel(productDetailsUseCase)

    @Test
    fun `initData should check getProductDetails Response`() {
        val response = ProductDetailResponse()
        coEvery { productDetailsUseCase(any()) } returns flowOf(Outcome.Success(response))
        viewModel.getProductDetails("1")
        testDispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.productDetailsState.value.response is ProductDetailResponse)
    }

    @Test
    fun `initData should check getProductDetails Error`() {
        val errorMessage = "Something went wrong!"
        coEvery { productDetailsUseCase(any()) } returns flowOf(Outcome.Error(any(), false, errorMessage))
        viewModel.getProductDetails("1")
        testDispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.productDetailsState.value.error.contentEquals(errorMessage))
    }
}