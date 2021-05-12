import model.BufferController
import model.BufferedCell
import model.GameSpace
import tornadofx.App
import tornadofx.launch
import view.MenuView

class GOLapp: App(MenuView::class)

fun main() {
    launch<GOLapp>();
}
