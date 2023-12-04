package com.systems.assignment.products.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsResponse(
    @SerializedName("products")
    val products: List<Product>? = null,
) : Parcelable