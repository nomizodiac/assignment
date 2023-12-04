package com.systems.assignment.products.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.systems.assignment.R
import com.systems.assignment.common.ui.components.TopSpacer
import com.systems.assignment.common.ui.theme.GreyColor
import com.systems.assignment.common.ui.theme.Typography
import com.systems.assignment.common.utils.isValidAmount
import com.systems.assignment.products.domain.model.ProductDetailResponse

@Composable
fun ProductDetailsScreen(
    product: ProductDetailResponse
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        TopSpacer(8.dp)
        val context = LocalContext.current

        Image(
            painter = rememberAsyncImagePainter(
                remember(product.image) {
                    ImageRequest.Builder(context)
                        .placeholder(R.drawable.ic_placeholder)
                        .data(product.image)
                        .diskCacheKey(product.image)
                        .memoryCacheKey(product.image)
                        .build()
                }
            ),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        )
        TopSpacer(16.dp)

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = product.name ?: "",
                style = Typography.bodyMedium.copy(
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                )
            )

            val amount: String =
                if (product.price.isValidAmount()) product.price.toString() + " PKR" else "0 PKR"
            Text(
                text = amount,
                style = Typography.bodySmall.copy(
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    color = GreyColor
                )
            )
            TopSpacer(8.dp)
            Text(
                text = product.description ?: "",
                style = Typography.bodySmall.copy(
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                )
            )
        }

    }
}
