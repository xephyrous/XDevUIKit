import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    val icon = painterResource("him.jpg")

    Window(onCloseRequest = ::exitApplication, title = "XDevUIKit Window", icon = icon) {
        App()
    }
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Row {
                Image(
                    painter = painterResource("XDevUIKit.png"),
                    contentDescription = "The UDevUIKit Logo",
                )
            }
        }
    }
}