package com.systems.assignment.products.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.systems.assignment.common.base.BaseViewModel
import com.systems.assignment.common.data.remote.Outcome
import com.systems.assignment.common.state.UIState
import com.systems.assignment.products.domain.model.ProductDetailRequest
import com.systems.assignment.products.domain.model.ProductDetailResponse
import com.systems.assignment.products.domain.usecase.ProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    val productDetailsUseCase: ProductDetailsUseCase
) : BaseViewModel() {

    private val _productDetailsState = mutableStateOf(UIState<ProductDetailResponse>())
    val productDetailsState: State<UIState<ProductDetailResponse>> = _productDetailsState
    private var productDetailsJob: Job? = null

    fun initData(args: ProductDetailsFragmentArgs) {
        runBlocking {
            getProductDetails(args.productId)
        }
    }

    fun getProductDetails(productId: String) {
        cancelJob(productDetailsJob)
        val request = ProductDetailRequest(productId = productId)
        productDetailsJob = productDetailsUseCase(request).onEach { result ->
            when (result) {
                is Outcome.Start -> {
                    _productDetailsState.value = UIState(isLoading = true)
                }
                is Outcome.Success -> {
                    val response = result.data
                    _productDetailsState.value = UIState(response = response)
                }
                is Outcome.Error -> {
                    _productDetailsState.value =
                        UIState(error = result.message ?: "Something went wrong!")
                }
                is Outcome.NetworkError -> {
                    _productDetailsState.value =
                        UIState(error = "network error")
                }
                is Outcome.End -> {
                    removeJob(productDetailsJob)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)

        addJob(productDetailsJob)
    }
}
