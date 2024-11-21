package xdevuikit.core.components

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import xdevuikit.core.utils.*

/**
 * Controls the animated state and position of a [FlexBox]
 *
 * @param initialSize The initial size of the FlexBox
 */
class FlexBoxController(
    initialSize: DpSize,
) {
    /** The mutable state of the [FlexBox]'s width */
    var targetWidth = mutableStateOf(initialSize.width)
        private set

    /** The mutable state of the [FlexBox]'s height */
    var targetHeight = mutableStateOf(initialSize.height)
        private set

    /** The duration of the [FlexBox]'s width, height, and position animations (in milliseconds) */
    var durationMs: Array<Int?> = arrayOf(1000, 1000, 1000)
        private set

    /** The easing functions to use for the [FlexBox]'s width, height, and position animation */
    var easing: Array<Easing?> = arrayOf(LinearEasing, LinearEasing, LinearEasing)
        private set

    /** Types of float actions the [FlexBox] can perform. Used for [revert] & [revertFloat] */
    enum class FloatAction {

    }

    /** The last size of the [FlexBox] */
    private var lastSize = DpSize(initialSize.width, initialSize.height)

    /** The mutable state of the [FlexBox]'s X position */
    var targetX = mutableStateOf(0.dp)

    /** The mutable state of the [FlexBox]'s Y position */
    var targetY = mutableStateOf(0.dp)

    /**
     * Sets the initial position of the [FlexBox] when it is created
     */
    fun setInitialPosition(x: Dp, y: Dp) {
        targetX.value = x
        targetY.value = y
    }

    /**
     * Animates a [FlexBox] to a specified size
     *
     * @param width The target width to animate to
     * @param height The target height to animate to
     * @param durationMs The times, in milliseconds, that the animations last
     * @param easing The easing functions to animate with (Defaults to [LinearEasing])
     */
    fun flex(
        width: Dp? = null,
        height: Dp? = null,
        durationMs: Array<Int?> = durations(1000, 1000),
        easing: Array<Easing?> = easings(LinearEasing, LinearEasing)
    ) {
        if (durationMs.size < 2 || easing.size < 2) { return }

        lastSize = DpSize(this.targetWidth.value, this.targetHeight.value)

        if (width != null) targetWidth.value = width
        if (height != null) targetHeight.value = height

        if (durationMs.first != null) this.durationMs.first = durationMs.first
        if (durationMs.second != null) this.durationMs.second = durationMs.second

        if (easing.first != null) this.easing.first = easing.first
        if (easing.second != null) this.easing.second = easing.second
    }

    /**
     * Helper function for [flex] that applies a single duration and easing to the width & height
     *
     * @param width The target width to animate to
     * @param height The target height to animate to
     * @param durationMs The time, in milliseconds, that the animation lasts
     * @param easing The easing function to animate with (Defaults to [LinearEasing])
     */
    fun flex(
        width: Dp? = null,
        height: Dp? = null,
        durationMs: Int = 1000,
        easing: Easing = LinearEasing,
    ) {
        flex(width, height, durations(durationMs, durationMs), easings(easing, easing))
    }

    /**
     * Animates to the last state the [FlexBox] was in (Animation and Position)
     */
    fun revert() {
        revertFlex()
        revertFloat()
    }

    /**
     * Animates to the last state the [FlexBox] was in
     */
    fun revertFlex() {
        flex(lastSize.width, lastSize.height, durationMs, easing)
    }

    /**
     * Animates to the last position the [FlexBox] was in
     */
    fun revertFloat() {

    }

    /**
     *
     */
    fun float() {

    }

    /**
     *
     */
    fun snap() {

    }
}

/**
 * A flexible box with built-in size and position controls
 *
 * @param modifier Any modifiers to the underlying box
 * @param initialSize The initial size of the box
 * @param contentAlignment How to align the internal content
 * @param controller The [FlexBoxController] associated with this box
 * @param content The internal content to display
 */
@Composable
fun FlexBox(
    modifier: Modifier = Modifier,
    initialSize: DpSize = DpSize(100.dp, 100.dp),
    contentAlignment: Alignment = Alignment.Center,
    controller: FlexBoxController = remember { FlexBoxController(initialSize) },
    content: @Composable FlexBoxController.() -> Unit
) {
    val width by animateDpAsState(
        targetValue = controller.targetWidth.value,
        animationSpec = tween(durationMillis = controller.durationMs.first!!, easing = controller.easing.first!!)
    )

    val height by animateDpAsState(
        targetValue = controller.targetHeight.value,
        animationSpec = tween(durationMillis = controller.durationMs.second!!, easing = controller.easing.second!!)
    )

    val x by animateDpAsState(
        targetValue = controller.targetX.value,
        animationSpec = tween(durationMillis = controller.durationMs.third!!, easing = controller.easing.third!!)
    )

    val y by animateDpAsState(
        targetValue = controller.targetY.value,
        animationSpec = tween(durationMillis = controller.durationMs.third!!, easing = controller.easing.third!!)
    )

    Box(
        modifier = Modifier
            .onGloballyPositioned { pos ->
                val pPos = pos.positionInParent()
                controller.setInitialPosition(pPos.x.dp, pPos.y.dp)
            }
            .size(width, height)
            //.offset(x, y)
            .then(modifier),
        contentAlignment = contentAlignment,
    ) {
        controller.content()
    }
}
