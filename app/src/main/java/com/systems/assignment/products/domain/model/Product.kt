package com.systems.assignment.products.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product")
data class Product(
    @SerializedName("product_id")
    @PrimaryKey
    var productId: String = "",
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("image")
    val image: String? = null
) : Parcelable