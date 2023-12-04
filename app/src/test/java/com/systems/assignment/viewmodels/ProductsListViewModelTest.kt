package com.systems.assignment.viewmodels

import com.systems.assignment.common.data.remote.Outcome
import com.systems.assignment.common.domain.usecase.DatabaseUseCase
import com.systems.assignment.products.domain.model.ProductsResponse
import com.systems.assignment.products.domain.usecase.ProductsListUseCase
import com.systems.assignment.products.presentation.listing.ProductsListViewModel
import com.systems.assignment.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.ArgumentMatchers.any

class ProductsListViewModelTest : BaseUnitTest() {

    private val productsListUseCase: ProductsListUseCase = mockk()
    private val databaseUseCase: DatabaseUseCase = mockk()
    private var viewModel = ProductsListViewModel(productsListUseCase, databaseUseCase)

    @Test
    fun `initData should check getProducts Response`() {
        val response = ProductsResponse(emptyList())
        coEvery { productsListUseCase(any()) } returns flowOf(Outcome.Success(response))
        coEvery { databaseUseCase.saveProducts(any()) } returns Unit
        viewModel.initData()
        testDispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.productsState.value.response is ProductsResponse)
    }

    @Test
    fun `initData should check getProducts Error`() {
        val errorMessage = "Something went wrong!"
        coEvery { productsListUseCase(any()) } returns flowOf(Outcome.Error(any(), false, errorMessage))
        coEvery { databaseUseCase.getProducts() } returns mockk()
        viewModel.initData()
        testDispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.productsState.value.error.contentEquals(errorMessage))
    }
}