package com.systems.assignment.products.presentation.details

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.systems.assignment.R
import com.systems.assignment.common.base.BaseFragment
import com.systems.assignment.common.ui.components.AppToolBar
import com.systems.assignment.common.ui.components.ErrorDialog
import com.systems.assignment.common.ui.components.GenericCircularIndicator
import com.systems.assignment.products.presentation.components.ProductDetailsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment() {

    private val viewModel: ProductDetailsViewModel by viewModels()

    private val args by navArgs<ProductDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initData(args)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {

                val productDetailsState = viewModel.productDetailsState.value
                val productDetailsResponse = remember {
                    mutableStateOf(productDetailsState.response)
                }

                when {
                    productDetailsState.networkError -> ""
                    productDetailsState.error.isNotEmpty() -> {
                        ErrorDialog(
                            title = stringResource(id = R.string.error),
                            message = productDetailsState.error
                        )
                    }
                    else -> {
                        productDetailsState.response?.let { response ->
                            productDetailsResponse.value = response
                        }
                    }
                }

                AppToolBar(
                    title = getString(R.string.product_details),
                    content = {
                        productDetailsResponse.value?.let { response ->
                            ProductDetailsScreen(product = response)
                        }
                    },
                    onBackPressed = {
                        findNavController().navigateUp()
                    }
                )

                ShowLoader()
            }
        }
    }

    @Composable
    fun ShowLoader() {
        val isLoading = viewModel.productDetailsState.value.isLoading
        GenericCircularIndicator(showLoading = isLoading)
    }
}