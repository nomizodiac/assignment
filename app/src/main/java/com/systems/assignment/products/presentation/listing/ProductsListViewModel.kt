package com.systems.assignment.products.presentation.listing

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.systems.assignment.common.base.BaseViewModel
import com.systems.assignment.common.data.remote.Outcome
import com.systems.assignment.common.domain.model.BaseRequest
import com.systems.assignment.common.domain.usecase.DatabaseUseCase
import com.systems.assignment.common.state.UIState
import com.systems.assignment.products.domain.model.ProductsResponse
import com.systems.assignment.products.domain.usecase.ProductsListUseCase
import com.systems.assignment.products.navigation.ProductsNavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    val productsListUseCase: ProductsListUseCase,
    private val databaseUseCase: DatabaseUseCase
) : BaseViewModel() {

    private val _router: MutableStateFlow<ProductsNavigationRoute> =
        MutableStateFlow(ProductsNavigationRoute.ProductsNone)
    val router: MutableStateFlow<ProductsNavigationRoute> = _router

    private val _productsState = mutableStateOf(UIState<ProductsResponse>())
    val productsState: State<UIState<ProductsResponse>> = _productsState
    private var productsJob: Job? = null

    fun initData() {
        runBlocking {
            getProducts()
        }
    }

    private fun getProducts() {
        cancelJob(productsJob)
        val request = BaseRequest()
        productsJob = productsListUseCase(request).onEach { result ->
            when (result) {
                is Outcome.Start -> {
                    _productsState.value = UIState(isLoading = true)
                }
                is Outcome.Success -> {
                    val response = result.data
                    _productsState.value = UIState(response = response)
                    // save data locally to use in offline mode
                    response.products?.let {
                        val list = it.take(3)
                        viewModelScope.launch {
                            databaseUseCase.saveProducts(list)
                        }
                    }
                }
                is Outcome.Error -> {
                    _productsState.value =
                        UIState(
                            error = result.message ?: "Something went wrong!",
                            response = ProductsResponse(databaseUseCase.getProducts())
                        )
                }
                is Outcome.NetworkError -> {
                    _productsState.value =
                        UIState(networkError = true, error = "Network error",
                                response = ProductsResponse(databaseUseCase.getProducts())
                        )
                }
                is Outcome.End -> {
                    removeJob(productsJob)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)

        addJob(productsJob)
    }

    fun navigateTo(route: ProductsNavigationRoute) {
        _router.value = route
    }
}
