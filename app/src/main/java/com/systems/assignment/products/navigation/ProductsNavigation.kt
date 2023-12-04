package com.systems.assignment.products.navigation

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.systems.assignment.products.presentation.listing.ProductsListFragment
import com.systems.assignment.products.presentation.listing.ProductsListFragmentDirections

class ProductsNavigation {

    fun navigate(sourceFragment: ProductsListFragment) {
        sourceFragment.lifecycleScope.launchWhenStarted {
            sourceFragment.viewModel.router.collect {
                when (it) {
                    is ProductsNavigationRoute.OpenProductDetails -> {
                        openProfileDetailsScreen(sourceFragment, it)
                    }
                    else -> {}
                }
                sourceFragment.viewModel.navigateTo(ProductsNavigationRoute.ProductsNone)
            }
        }
    }

    private fun openProfileDetailsScreen(
        sourceFragment: ProductsListFragment,
        it: ProductsNavigationRoute.OpenProductDetails
    ) {
        it.productId?.let { id ->
            val direction =
                ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(id)
            sourceFragment.findNavController().navigate(direction)
        }
    }
}