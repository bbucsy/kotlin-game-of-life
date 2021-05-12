import tornadofx.App
import tornadofx.launch
import view.GameStyle
import view.GameView

class GOLapp: App(GameView::class, GameStyle::class)

fun main() {
launch<GOLapp>()
}
