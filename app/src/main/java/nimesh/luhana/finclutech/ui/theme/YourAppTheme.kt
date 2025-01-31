// ui/theme/Theme.kt
package nimesh.luhana.finclutech.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import nimesh.luhana.finclutech.utils.LocalAppLanguage

@Composable
fun YourAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val layoutDirection = if (LocalAppLanguage.current.language == "ar") {
        LayoutDirection.Rtl
    } else {
        LayoutDirection.Ltr
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides layoutDirection,
            content = content
        )
    }
}