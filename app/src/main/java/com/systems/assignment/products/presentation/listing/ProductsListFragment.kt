package com.systems.assignment.products.presentation.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.viewModels
import com.systems.assignment.R
import com.systems.assignment.common.base.BaseFragment
import com.systems.assignment.common.ui.components.AppToolBar
import com.systems.assignment.common.ui.components.ErrorDialog
import com.systems.assignment.common.ui.components.GenericCircularIndicator
import com.systems.assignment.products.navigation.ProductsNavigation
import com.systems.assignment.products.navigation.ProductsNavigationRoute
import com.systems.assignment.products.presentation.components.ProductsScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsListFragment : BaseFragment() {

    @Inject
    lateinit var navigation: ProductsNavigation

    val viewModel: ProductsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {

                val productsState = viewModel.productsState.value

                val productsResponse = remember { mutableStateOf(productsState.response) }
                productsState.response?.let { response ->
                    productsResponse.value = response
                }

                AppToolBar(
                    title = getString(R.string.products),
                    showBackButton = false,
                    showNetworkError = productsState.networkError,
                    content = {
                        productsResponse.value?.let { response ->
                            ProductsScreen(response) {
                                viewModel.navigateTo(ProductsNavigationRoute.OpenProductDetails(it.productId))
                            }
                        }
                    },
                    onNetworkErrorRetry = {
                        viewModel.initData()
                    },
                    onBackPressed = {
                        activity?.finish()
                    }
                )

                if(!productsState.networkError && productsState.error.isNotEmpty()) {
                    ErrorDialog(
                        title = stringResource(id = R.string.error),
                        message = productsState.error
                    )
                }

                ShowLoader()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation.navigate(this)
    }

    @Composable
    fun ShowLoader() {
        val isLoading = viewModel.productsState.value.isLoading
        GenericCircularIndicator(showLoading = isLoading)
    }
}