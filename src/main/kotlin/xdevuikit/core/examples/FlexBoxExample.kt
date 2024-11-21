package xdevuikit.core.examples

import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import xdevuikit.core.components.FlexBox
import xdevuikit.core.utils.DpSize
import xdevuikit.core.utils.durations
import xdevuikit.core.utils.easings

fun main() = application {
    val icon = painterResource("him.jpg")

    Window(onCloseRequest = ::exitApplication, title = "XDevUIKit Window", icon = icon) {
        App()
    }
}

@Composable
@Preview
fun App() {
    var textA by remember { mutableStateOf("flex()") }
    var textB by remember { mutableStateOf("flexWidth()") }
    var textC by remember { mutableStateOf("flexHeight()") }
    var textD by remember { mutableStateOf("flexWidth() & flexHeight()") }

    MaterialTheme {
        Row {
            FlexBox(
                modifier = Modifier
                    .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                    .border(3.dp, Color.Black, RoundedCornerShape(10.dp)),
                initialSize = DpSize(150.dp, 100.dp)
            ) {
                Button(
                    onClick = {
                        if (textA == "flex()") {
                            textA = "Ta-da!"
                            flex(200.dp, 200.dp, 500, EaseInOut)
                            return@Button
                        }

                        textA = "flex()"
                        revertFlex()
                    }
                ) { Text(textA) }
            }

            FlexBox(
                modifier = Modifier
                    .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                    .border(3.dp, Color.Black, RoundedCornerShape(10.dp)),
                initialSize = DpSize(150.dp, 100.dp)
            ) {
                Button(
                    onClick = {
                        if (textB == "flexWidth()") {
                            textB = "Ta-da!"
                            flex(width = 200.dp, easing = EaseInOut)
                            return@Button
                        }

                        textB = "flexWidth()"
                        revertFlex()
                    }
                ) { Text(textB) }
            }

            FlexBox(
                modifier = Modifier
                    .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                    .border(3.dp, Color.Black, RoundedCornerShape(10.dp)),
                initialSize = DpSize(150.dp, 100.dp)
            ) {
                Button(
                    onClick = {
                        if (textC == "flexHeight()") {
                            textC = "Ta-da!"
                            flex(height = 200.dp, durationMs = 500, easing = EaseInOut)
                            return@Button
                        }

                        textC = "flexWidth()"
                        revertFlex()
                    }
                ) { Text(textC) }
            }

            FlexBox(
                modifier = Modifier
                    .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                    .border(3.dp, Color.Black, RoundedCornerShape(10.dp)),
                initialSize = DpSize(150.dp, 100.dp)
            ) {
                Button(
                    onClick = {
                        if (textD == "flexWidth() & flexHeight()") {
                            textD = "Ta-da!"
                            flex(
                                200.dp, 200.dp,
                                durations(1000, 500),
                                easings(EaseInOutCubic, LinearEasing)
                            )
                            return@Button
                        }

                        textD = "flexWidth() & flexHeight()"
                        revertFlex()
                    }
                ) { Text(textD) }
            }
        }
    }
}