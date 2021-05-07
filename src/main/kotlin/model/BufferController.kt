package model

class BufferController(val layers: Int = 2) {

    var readLayer: Int = 0
        private  set

    var writeLayer: Int = 1
        private set

    fun swapBuffers() {
        readLayer = writeLayer;
        writeLayer = (writeLayer + 1) % layers
    }

}
