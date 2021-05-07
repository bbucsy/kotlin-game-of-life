package model


class BufferedCell(private val controller: BufferController, initial: Boolean = false) {

    private val buffer = BooleanArray(controller.layers)

    init {
        buffer[controller.readLayer] = initial
    }

    var value: Boolean
    get() = buffer[controller.readLayer]
    set(value) {
        buffer[controller.readLayer] = value
    }

    var nextValue : Boolean
    get() = buffer[controller.writeLayer]
    set(value) {buffer[controller.writeLayer] = value}

    override fun toString(): String {
        //return "${if(buffer[controller.readLayer]) "1" else "0"}[${if(buffer[controller.writeLayer]) "1" else "0"}]"
        return if(buffer[controller.readLayer]) "1" else "0"
    }

}