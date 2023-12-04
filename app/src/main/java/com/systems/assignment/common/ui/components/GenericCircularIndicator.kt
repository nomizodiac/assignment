package com.systems.assignment.common.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.systems.assignment.common.ui.theme.Primary

@Composable
fun GenericCircularIndicator(modifier: Modifier = Modifier, showLoading: Boolean, size: Dp = 60.dp) {
    var showLoader = showLoading
    if (showLoader) {
        Dialog(
            onDismissRequest = { showLoader = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator(
                    modifier = modifier
                        .size(size)
                        .padding(0.dp, 0.dp, 0.dp, 0.dp),
                    color = Primary,
                    strokeWidth = 2.dp,
                )
            }
        }
    }
}