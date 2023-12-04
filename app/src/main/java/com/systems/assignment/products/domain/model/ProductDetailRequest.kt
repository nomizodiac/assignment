package com.systems.assignment.products.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetailRequest(
    @SerializedName("productId")
    val productId: String? = null,
) : Parcelable