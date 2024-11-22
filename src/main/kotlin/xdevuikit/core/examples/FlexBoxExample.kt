package xdevuikit.core.examples

import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import xdevuikit.core.components.FlexBox
import xdevuikit.core.utils.DpSize
import xdevuikit.core.utils.durations
import xdevuikit.core.utils.easings

fun main() = application {
    val icon = painterResource("him.jpg")

    Window(onCloseRequest = ::exitApplication, title = "XDevUIKit Window", icon = icon) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = Color(0xff49464a)),
        ) {
            App()
        }
    }
}

@Composable
@Preview
fun App() {
    var textA by remember { mutableStateOf("flex()") }
    var textB by remember { mutableStateOf("flex() Width") }
    var textC by remember { mutableStateOf("flex() Height") }
    var textD by remember { mutableStateOf("flex() & float()") }
    var textE by remember { mutableStateOf("float()") }
    var textF by remember { mutableStateOf("float() X") }
    var textG by remember { mutableStateOf("float() Y") }
    var textH by remember { mutableStateOf("float() & flex()") }

    MaterialTheme {
        // Title
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.offset(y = 75.dp)
                    .background(Color(0xff2d2a2e), shape = RoundedCornerShape(5.dp))
                    .border(4.dp, Color(0xff232024), RoundedCornerShape(5.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp),
            ) {
                Text(
                    text = "FlexBox Examples",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff61afef),
                    fontSize = 30.sp
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Top Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FlexBox(
                        modifier = Modifier
                            .background(Color(0xff2d2a2e), shape = RoundedCornerShape(10.dp))
                            .border(4.dp, Color(0xff232024), RoundedCornerShape(10.dp)),
                        initialSize = DpSize(150.dp, 100.dp)
                    ) {
                        Button(
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color(0xff61afef)
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                focusedElevation = 0.dp
                            ),
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
                            .background(Color(0xff2d2a2e), shape = RoundedCornerShape(10.dp))
                            .border(4.dp, Color(0xff232024), RoundedCornerShape(10.dp)),
                        initialSize = DpSize(150.dp, 100.dp)
                    ) {
                        Button(
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color(0xff61afef)
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                focusedElevation = 0.dp
                            ),
                            onClick = {
                                if (textB == "flex() Width") {
                                    textB = "Ta-da!"
                                    flex(width = 200.dp, easing = EaseInOut)
                                    return@Button
                                }

                                textB = "flex() Width"
                                revertFlex()
                            }
                        ) { Text(textB) }
                    }

                    FlexBox(
                        modifier = Modifier
                            .background(Color(0xff2d2a2e), shape = RoundedCornerShape(10.dp))
                            .border(4.dp, Color(0xff232024), RoundedCornerShape(10.dp)),
                        initialSize = DpSize(150.dp, 100.dp)
                    ) {
                        Button(
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color(0xff61afef)
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                focusedElevation = 0.dp
                            ),
                            onClick = {
                                if (textC == "flex() Height") {
                                    textC = "Ta-da!"
                                    flex(height = 200.dp, durationMs = 500, easing = EaseInOut)
                                    return@Button
                                }

                                textC = "flex() Height"
                                revertFlex()
                            }
                        ) { Text(textC) }
                    }

                    FlexBox(
                        modifier = Modifier
                            .background(Color(0xff2d2a2e), shape = RoundedCornerShape(10.dp))
                            .border(4.dp, Color(0xff232024), RoundedCornerShape(10.dp)),
                        initialSize = DpSize(150.dp, 100.dp)
                    ) {
                        Button(
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color(0xff61afef)
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                focusedElevation = 0.dp
                            ),
                            onClick = {
                                if (textD == "flex() & float()") {
                                    textD = "Ta-da!"
                                    flex(
                                        200.dp, 200.dp,
                                        durations(1000, 500),
                                        easings(EaseInOutCubic, LinearEasing)
                                    )
                                    float(
                                        50.dp, 50.dp,
                                        durations(1000, 500),
                                        easings(EaseInOutCubic, LinearEasing)
                                    )
                                    return@Button
                                }

                                textD = "flex() & float()"
                                revert()
                            }
                        ) { Text(textD) }
                    }
                }

                // Middle row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FlexBox(
                        modifier = Modifier
                            .background(Color(0xff2d2a2e), shape = RoundedCornerShape(10.dp))
                            .border(4.dp, Color(0xff232024), RoundedCornerShape(10.dp)),
                        initialSize = DpSize(150.dp, 100.dp)
                    ) {
                        Button(
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color(0xff61afef)
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                focusedElevation = 0.dp
                            ),
                            onClick = {
                                if (textE == "float()") {
                                    textE = "Ta-da!"
                                    float(50.dp, 50.dp, 500, EaseInOut)
                                    return@Button
                                }

                                textE = "float()"
                                revertFloat()
                            }
                        ) { Text(textE) }
                    }

                    FlexBox(
                        modifier = Modifier
                            .background(Color(0xff2d2a2e), shape = RoundedCornerShape(10.dp))
                            .border(4.dp, Color(0xff232024), RoundedCornerShape(10.dp)),
                        initialSize = DpSize(150.dp, 100.dp)
                    ) {
                        Button(
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color(0xff61afef)
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                focusedElevation = 0.dp
                            ),
                            onClick = {
                                if (textF == "float() X") {
                                    textF = "Ta-da!"
                                    float(x = 50.dp, easing = EaseInOut)
                                    return@Button
                                }

                                textF = "float() X"
                                revertFloat()
                            }
                        ) { Text(textF) }
                    }

                    FlexBox(
                        modifier = Modifier
                            .background(Color(0xff2d2a2e), shape = RoundedCornerShape(10.dp))
                            .border(4.dp, Color(0xff232024), RoundedCornerShape(10.dp)),
                        initialSize = DpSize(150.dp, 100.dp)
                    ) {
                        Button(
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color(0xff61afef)
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                focusedElevation = 0.dp
                            ),
                            onClick = {
                                if (textG == "float() Y") {
                                    textG = "Ta-da!"
                                    float(y = 50.dp, durationMs = 500, easing = EaseInOut)
                                    return@Button
                                }

                                textG = "float() Y"
                                revertFloat()
                            }
                        ) { Text(textG) }
                    }

                    FlexBox(
                        modifier = Modifier
                            .background(Color(0xff2d2a2e), shape = RoundedCornerShape(10.dp))
                            .border(4.dp, Color(0xff232024), RoundedCornerShape(10.dp)),
                        initialSize = DpSize(150.dp, 100.dp)
                    ) {
                        Button(
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color(0xff61afef)
                            ),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                                focusedElevation = 0.dp
                            ),
                            onClick = {
                                if (textH == "float() & flex()") {
                                    textH = "Ta-da!"
                                    float(
                                        50.dp, 50.dp,
                                        durations(1000, 500),
                                        easings(EaseInOutCubic, LinearEasing)
                                    )
                                    flex(
                                        200.dp, 200.dp,
                                        durations(1000, 500),
                                        easings(EaseInOutCubic, LinearEasing)
                                    )
                                    return@Button
                                }

                                textH = "float() & flex()"
                                revert()
                            }
                        ) { Text(textH) }
                    }
                }
            }
        }
    }
}