package com.systems.assignment.common.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.systems.assignment.R
import com.systems.assignment.common.ui.theme.AppTheme
import com.systems.assignment.common.ui.theme.LightGrey
import com.systems.assignment.common.ui.theme.Typography
import com.systems.assignment.common.ui.theme.rememberWindowSizeClass
import com.systems.assignment.common.utils.clickableNoRipple

@Composable
fun AppToolBar(
    @DrawableRes icon: Int = R.drawable.icon_nav_back,
    topBarColor: Color = White,
    title: String = "",
    showBackButton: Boolean = true,
    showNetworkError: Boolean = false,
    content: @Composable () -> Unit,
    onBackPressed: () -> Unit = {},
    onNetworkErrorRetry: () -> Unit = {}
) {
    val window = rememberWindowSizeClass()
    AppTheme(window) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = topBarColor)
            ) {
                val (backIcon, titleText, trailingIconText) = createRefs()

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp)
                    .constrainAs(backIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                    if(showBackButton) {
                        Image(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterStart)
                                .clickableNoRipple { onBackPressed() },
                            painter = painterResource(id = icon),
                            contentDescription = null
                        )
                    }
                }

                Text(
                    text = title,
                    modifier = Modifier
                        .constrainAs(titleText) {

                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)

                            width = Dimension.fillToConstraints
                        }
                        .padding(horizontal = 60.dp),
                    color = Black,
                    style = Typography.headlineSmall.copy(
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 16.dp)
                    .constrainAs(trailingIconText) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                }
            }

            Divider(
                color = LightGrey
            )

            Box(modifier = Modifier.fillMaxSize()){

                content.invoke()
                if(showNetworkError) {
                    NetworkError(modifier = Modifier.align(Alignment.BottomCenter), onNetworkErrorRetry)
                }
            }
        }
    }
}