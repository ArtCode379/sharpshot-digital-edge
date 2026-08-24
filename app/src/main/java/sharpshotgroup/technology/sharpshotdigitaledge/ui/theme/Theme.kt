package sharpshotgroup.technology.sharpshotdigitaledge.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val SharpColorScheme = lightColorScheme(
    primary = SharpBlue,
    onPrimary = CloudSurface,
    secondary = DigitalCyan,
    onSecondary = Ink,
    background = CloudBackground,
    onBackground = Ink,
    surface = CloudSurface,
    onSurface = Ink,
    surfaceVariant = ChipBackground,
    onSurfaceVariant = Muted,
    outline = Border,
    error = Color(0xFFBA1A1A),
)

@Composable
fun ServiceSkeletonTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = SharpColorScheme,
        typography = AppTypography,
        content = content,
    )
}
