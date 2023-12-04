package com.systems.assignment.products.presentation.components.portrait

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.systems.assignment.R
import com.systems.assignment.common.ui.components.TopSpacer
import com.systems.assignment.common.ui.theme.GreyColor
import com.systems.assignment.common.ui.theme.Typography
import com.systems.assignment.common.utils.clickableNoRipple
import com.systems.assignment.common.utils.isValidAmount
import com.systems.assignment.products.domain.model.Product

@Composable
fun ProductsPortrait(
    productsList: List<Product>?,
    onItemClick: (Product) -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        item { TopSpacer(8.dp) }
        productsList?.size?.let {
            items(it) { item ->
                ProductListItem(productsList[item], onItemClick)
            }
        }
        item { TopSpacer(8.dp) }
    }
}

@Composable
fun ProductListItem(product: Product, onItemClick: (Product) -> Unit) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
            .clickableNoRipple { onItemClick(product) }
    ) {
        val (image, title, price, arrow) = createRefs()

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
                .size(44.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = product.name?: "",
            style = Typography.bodyMedium.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(title) {
                    start.linkTo(image.end)
                    end.linkTo(arrow.start)
                    top.linkTo(image.top)
                    bottom.linkTo(price.top)
                    width = Dimension.fillToConstraints
                }
        )

        val amount: String =
            if (product.price.isValidAmount()) product.price.toString() + " PKR" else "0 PKR"
        Text(
            text = amount,
            style = Typography.bodySmall.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                color = GreyColor
            ),
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(price) {
                    start.linkTo(image.end)
                    end.linkTo(arrow.start)
                    top.linkTo(title.bottom)
                    bottom.linkTo(image.bottom)
                    width = Dimension.fillToConstraints
                }
        )

        Image(
            painterResource(id = R.drawable.ic_arrow),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(arrow) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}