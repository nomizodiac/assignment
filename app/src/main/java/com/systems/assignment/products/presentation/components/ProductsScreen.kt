package com.systems.assignment.products.presentation.components

import androidx.compose.runtime.Composable
import com.systems.assignment.common.ui.theme.Orientation
import com.systems.assignment.common.ui.theme.Theme
import com.systems.assignment.products.domain.model.Product
import com.systems.assignment.products.domain.model.ProductsResponse
import com.systems.assignment.products.presentation.components.landscape.ProductsLandscape
import com.systems.assignment.products.presentation.components.portrait.ProductsPortrait

@Composable
fun ProductsScreen(
    response: ProductsResponse,
    onItemClick: (Product) -> Unit
) {
    val productsList = response.products
    when (Theme.orientation) {
        Orientation.Portrait -> ProductsPortrait(productsList, onItemClick)
        else -> ProductsLandscape(productsList, onItemClick)
    }
}
