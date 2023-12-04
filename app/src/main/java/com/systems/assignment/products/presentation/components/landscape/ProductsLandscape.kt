package com.systems.assignment.products.presentation.components.landscape

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.systems.assignment.R
import com.systems.assignment.common.ui.theme.GreyColor
import com.systems.assignment.common.ui.theme.Typography
import com.systems.assignment.common.utils.clickableNoRipple
import com.systems.assignment.common.utils.isValidAmount
import com.systems.assignment.products.domain.model.Product

@Composable
fun ProductsLandscape(
    productsList: List<Product>?,
    onItemClick: (Product) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 16.dp,
            end = 20.dp,
            bottom = 16.dp
        ),
        content = {
            productsList?.size?.let {
                items(it) { item ->
                    ProductGridItem(productsList[item], onItemClick)
                }
            }
        })
}

@Composable
fun ProductGridItem(product: Product, onItemClick: (Product) -> Unit) {

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
                .size(56.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = product.name?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Typography.bodyMedium.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(image.bottom)
                    bottom.linkTo(price.top)
                }
        )

        val amount: String =
            if (product.price.isValidAmount()) product.price.toString() + " PKR" else "0 PKR"
        Text(
            text = amount,
            maxLines = 1,
            style = Typography.bodySmall.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                color = GreyColor
            ),
            modifier = Modifier
                .constrainAs(price) {
                    start.linkTo(parent.start)
                    top.linkTo(title.bottom)
                }
        )

        Image(
            painterResource(id = R.drawable.ic_arrow),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(arrow) {
                    end.linkTo(parent.end)
                    top.linkTo(title.top)
                    bottom.linkTo(price.bottom)
                }
        )
    }
}