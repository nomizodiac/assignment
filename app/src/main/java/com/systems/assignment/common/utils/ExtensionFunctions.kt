package com.systems.assignment.common.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Stable
fun Modifier.clickableNoRipple(
    eventCallBack: () -> Unit,
): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        eventCallBack()
    }
}

fun Int?.isValidAmount() : Boolean {
    if (this != null && this > 0) return true
    return false
}
