package com.systems.assignment.common.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.systems.assignment.common.ui.LocalAppDimens
import com.systems.assignment.common.ui.LocalOrientationMode
import com.systems.assignment.common.ui.UIUtils

private val LightColorPalette = lightColorScheme(
    primary = TextColor,
    secondary = TextColor,
    background = AppBackground,
    onSurface = AppBackground,
    onPrimary = TextColor,
    onSecondary = TextColor,
    onBackground = TextColor,
)

@Composable
fun AppTheme(
    windowSizeClass: WindowSizeClass,
    content: @Composable () -> Unit,
) {
    val colorScheme = LightColorPalette

    val orientation = when{
        windowSizeClass.width.size > windowSizeClass.height.size -> Orientation.Landscape
        else -> Orientation.Portrait
    }

    val size = when(orientation){
        Orientation.Portrait -> windowSizeClass.width
        else -> windowSizeClass.height
    }

    val dimensions = when(size){
        is WindowSize.Small -> smallDimensions
        is WindowSize.Compact -> compactDimensions
        is WindowSize.Medium -> mediumDimensions
        else -> largeDimensions
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides
                if (LocalConfiguration.current.layoutDirection == LayoutDirection.Rtl.ordinal)
                    LayoutDirection.Rtl
                else LayoutDirection.Ltr
    ) {
        UIUtils(dimensions = dimensions, orientation = orientation) {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = Typography,
                shapes = Shapes,
                content = content
            )
        }
    }
}

object Theme {
    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current

    val orientation: Orientation
        @Composable
        get() = LocalOrientationMode.current
}