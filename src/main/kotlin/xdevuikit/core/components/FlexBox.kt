package xdevuikit.core.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
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

    /**
     *  The duration of the [FlexBox]'s width, height, and position animations (in milliseconds)
     *
     *  _Stored as (width, height, x, y)_
     */
    var durationMs: Array<Int?> = arrayOf(1000, 1000, 1000, 1000)
        private set

    /** The easing functions to use for the [FlexBox]'s width, height, and position animation
     *
     * _Stored as (width, height, x, y)_
     */
    var easing: Array<Easing?> = arrayOf(LinearEasing, LinearEasing, LinearEasing, LinearEasing)
        private set

    /** The last size of the [FlexBox] */
    private var lastSize = DpSize(initialSize.width, initialSize.height)

    /** The last position of the [FlexBox] */
    private var lastPos = DpOffset(0.dp, 0.dp)

    /** The mutable state of the [FlexBox]'s X position */
    var targetX = mutableStateOf(0.dp)

    /** The mutable state of the [FlexBox]'s Y position */
    var targetY = mutableStateOf(0.dp)

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
     * @param durationMs The time, in milliseconds, that the animations last
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
        float(lastPos.x, lastPos.y, durationMs.takeLast(2).toTypedArray(), easing.takeLast(2).toTypedArray())
    }

    /**
     * Animates a [FlexBox] to a specified position
     *
     * @param x The target x to animate to
     * @param y The target y to animate to
     * @param durationMs The times, in milliseconds, that the animations last
     * @param easing The easing functions to animate with (Defaults to [LinearEasing])
     */
    fun float(
        x: Dp? = null,
        y: Dp? = null,
        durationMs: Array<Int?> = durations(1000, 1000),
        easing: Array<Easing?> = easings(LinearEasing, LinearEasing)
    ) {
        if (durationMs.size < 2 || easing.size < 2) { return }

        lastPos = DpOffset(this.targetX.value, this.targetY.value)

        if (x != null) targetX.value = x
        if (y != null) targetY.value = y

        if (durationMs.first != null) this.durationMs[2] = durationMs.first
        if (durationMs.second != null) this.durationMs[3] = durationMs.second

        if (easing.first != null) this.easing[2] = easing.first
        if (easing.second != null) this.easing[3] = easing.second
    }

    /**
     * Helper function for [float] that applies a single duration and easing to the x & y
     *
     * @param x The target X to animate to
     * @param y The target Y to animate to
     * @param durationMs The time, in milliseconds, that the animation lasts
     * @param easing The easing function to animate with (Defaults to [LinearEasing])
     */
    fun float(
        x: Dp? = null,
        y: Dp? = null,
        durationMs: Int = 1000,
        easing: Easing = LinearEasing,
    ) {
        float(x, y, durations(durationMs, durationMs), easings(easing, easing))
    }

    /**
     * Immediately moves the [FlexBox] to a position
     *
     * @param x The X position to move to
     * @param y The Y position to move to
     */
    fun snap(
        x: Dp? = null,
        y: Dp? = null,
    ) {
        float(x, y, durations(0, 0), easings(Ease, Ease))
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
    /** The mutable state of the box's width */
    val width by animateStateWithCallback(
        targetValue = controller.targetWidth.value,
        animationSpec = tween(durationMillis = controller.durationMs.first!!, easing = controller.easing.first!!),
        callback = { }
    )

    /** The mutable state of the box's height */
    val height by animateStateWithCallback(
        targetValue = controller.targetHeight.value,
        animationSpec = tween(durationMillis = controller.durationMs.second!!, easing = controller.easing.second!!),
        callback = { }
    )

    /** The mutable state of the box's x position */
    val x by animateStateWithCallback(
        targetValue = controller.targetX.value,
        animationSpec = tween(durationMillis = controller.durationMs[2]!!, easing = controller.easing[2]!!),
        callback = { }
    )

    /** The mutable state of the box's y position */
    val y by animateStateWithCallback(
        targetValue = controller.targetY.value,
        animationSpec = tween(durationMillis = controller.durationMs[3]!!, easing = controller.easing[3]!!),
        callback = { }
    )

    Box(
        modifier = Modifier
            .size(width, height)
            .offset(x, y)
            .then(modifier),
        contentAlignment = contentAlignment,
    ) {
        controller.content()
    }
}
