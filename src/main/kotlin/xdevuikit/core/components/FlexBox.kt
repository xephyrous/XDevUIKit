package xdevuikit.core.components

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import xdevuikit.core.utils.DpSize
import xdevuikit.core.utils.first
import xdevuikit.core.utils.second
import xdevuikit.core.utils.third

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

    /** Types of flex actions the [FlexBox] can perform. Used for [revert] & [revertFlex] */
    private enum class FlexAction {
        None,
        FlexSize,
        FlexWidth,
        FlexHeight
    }

    /** Types of float actions the [FlexBox] can perform. Used for [revert] & [revertFloat] */
    enum class FloatAction {

    }

    /** The last flex action performed on the [FlexBox] */
    private var lastAction = FlexAction.None

    /** The last size of the [FlexBox] */
    private var lastSize = DpSize(initialSize.width, initialSize.height)

    /** Whether to link [flexWidth] & [flexHeight] calls */
    private var linked = false

    /** The mutable state of the [FlexBox]'s X position */
    var targetX = mutableStateOf(0.dp)

    /** The mutable state of the [FlexBox]'s Y position */
    var targetY = mutableStateOf(0.dp)

    /**
     * Animates a [FlexBox] to a specified size
     *
     * @param targetSize The target size to animate to
     * @param durationMs The time, in milliseconds, that the animation lasts
     * @param easing The easing function to animate with (Defaults to [LinearEasing])
     */
    fun flex(
        targetSize: DpSize,
        durationMs: Int = 1000,
        easing: Easing = LinearEasing
    ) {
        lastAction = FlexAction.FlexSize
        lastSize = DpSize(this.targetWidth.value, this.targetHeight.value)

        targetWidth.value = targetSize.width
        targetHeight.value = targetSize.height

        this.durationMs = arrayOf(durationMs, durationMs, this.durationMs.third)
        this.easing = arrayOf(easing, easing, this.easing.third)
    }

    /**
     * Internal overload of [flex] that supports width and height specification
     *
     * @param targetSize The target size to animate to
     * @param durationMs The time, in milliseconds, that the width and/or height animate to (skips if null)
     * @param easing The easing function to animate with (Defaults to [LinearEasing])
     */
    private fun _flex(
        targetSize: DpSize,
        durationMs: Array<Int?>,
        easing: Array<Easing?>,
    ) {
        targetWidth.value = targetSize.width
        targetHeight.value = targetSize.height

        if (durationMs.first != null) this.durationMs.first = durationMs.first
        if (durationMs.second != null) this.durationMs.second = durationMs.second

        if (easing.first != null) this.easing.first = easing.first
        if (easing.second != null) this.easing.second = easing.second
    }

    /**
     * Animates a [FlexBox] to a specified size
     *
     * @param width The target width to animate to
     * @param durationMs The time, in milliseconds, that the animation lasts
     * @param easing The easing function to animate with (Defaults to [LinearEasing])
     */
    fun flexWidth(
        width: Dp,
        durationMs: Int,
        easing: Easing = LinearEasing
    ) {
        lastAction = if (linked) FlexAction.FlexSize else FlexAction.FlexSize
        if (!linked) lastSize = DpSize(this.targetWidth.value, this.targetHeight.value)

        _flex(DpSize(width, this.targetHeight.value), arrayOf(durationMs, null), arrayOf(easing, null))
    }

    /**
     * Animates a [FlexBox] to a specified size
     *
     * @param height The target height to animate to
     * @param durationMs The time, in milliseconds, that the animation lasts
     * @param easing The easing function to animate with (Defaults to [LinearEasing])
     */
    fun flexHeight(
        height: Dp,
        durationMs: Int,
        easing: Easing = LinearEasing
    ) {
        lastAction = if (linked) FlexAction.FlexSize else FlexAction.FlexHeight
        if (!linked) lastSize = DpSize(this.targetWidth.value, this.targetHeight.value)

        _flex(DpSize(this.targetWidth.value, height), arrayOf(null, durationMs), arrayOf(null, easing))
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
        when (lastAction) {
            FlexAction.FlexSize -> {
                if (!linked) {
                    flex(lastSize, durationMs.first!!, easing.first!!)
                    return
                }

                _flex(lastSize, durationMs, easing)
                lastAction = FlexAction.FlexSize
            }
            FlexAction.FlexWidth -> flexWidth(lastSize.width, durationMs.first!!, easing.first!!)
            FlexAction.FlexHeight -> flexHeight(lastSize.height, durationMs.second!!, easing.second!!)
            FlexAction.None -> { /* Nothing To Do! */ }
        }
    }

    /**
     * Animates to the last position the [FlexBox] was in
     */
    fun revertFloat() {

    }

    /**
     * Links [flexWidth] and [flexHeight] calls for [revert] & [unflex]
     */
    fun link() {
        linked = true
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

    Box(
        modifier = Modifier
            .size(width, height)
            .then(modifier),
        contentAlignment = contentAlignment,
    ) {
        controller.content()
    }
}
