package model

import kotlin.properties.Delegates

/**
 * Holds a boolean value, with a buffer
 *
 * The buffer value can be written, changing the actual value.
 * When the buffer value is written to the actual value, a
 * registered handle function is invoked
 */
class BufferedCell(initial: Boolean = false) {


    /**
     * The current value of the cell.
     * If changed, it fires the onChange event.
     */
    var value: Boolean by Delegates.observable(initial) { _, oldValue, newValue ->
        onChange?.invoke(oldValue, newValue)
    }

    /**
     * The buffer of the cell, that will be next value after swapping the buffers.
     */
    var buffer: Boolean = false

    /**
     * The handle function that will be fired when the value is changed.
     * Only will be fired if the next value is different from the current value.
     */
    var onChange: ((oldValue: Boolean, newValue: Boolean) -> Unit)? = null

    /**
     * Inverts the current value of the cell.
     */
    fun invertValue() {
        value = !value
    }

    /**
     * Swaps the buffer
     */
    fun swapBuffer() {
        if (buffer != value)
            value = buffer
    }

}