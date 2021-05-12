package model

import kotlin.properties.Delegates


class BufferedCell(initial: Boolean = false) {

    var value: Boolean by Delegates.observable(initial) { _, oldValue, newValue ->
        onChange?.invoke(oldValue, newValue)
    }
    var buffer: Boolean = false

    var onChange: ((oldValue: Boolean, newValue: Boolean) -> Unit)? = null

    fun invertValue(){ value = !value }

    fun swapBuffer() {
        if (buffer != value)
            value = buffer
    }

}