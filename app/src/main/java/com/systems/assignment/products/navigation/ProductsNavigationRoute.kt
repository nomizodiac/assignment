package com.systems.assignment.products.navigation

sealed class ProductsNavigationRoute {
    object ProductsNone: ProductsNavigationRoute()
    data class OpenProductDetails(val productId:String?): ProductsNavigationRoute()
}