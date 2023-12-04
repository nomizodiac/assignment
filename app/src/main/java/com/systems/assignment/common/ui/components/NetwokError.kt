package com.systems.assignment.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.systems.assignment.R
import com.systems.assignment.common.ui.theme.Primary
import com.systems.assignment.common.ui.theme.Typography
import com.systems.assignment.common.utils.clickableNoRipple

@Composable
fun NetworkError(
    modifier: Modifier = Modifier,
    onNetworkErrorRetry: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Primary)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = stringResource(id = R.string.offline),
                style = Typography.bodyMedium.copy(
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    color = Color.White,
                    fontWeight = Bold
                )
            )
            TopSpacer(4.dp)
            Text(
                text = stringResource(id = R.string.check_connection_retry),
                style = Typography.bodySmall.copy(
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    color = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.retry).uppercase(),
            style = Typography.bodyMedium.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                color = Color.Black,
                fontWeight = Bold
            ),
            modifier = Modifier.clickableNoRipple { onNetworkErrorRetry() }
        )
    }
}