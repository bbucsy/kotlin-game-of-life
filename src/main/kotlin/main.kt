import tornadofx.App
import tornadofx.launch
import view.GameView

class GOLapp: App(GameView::class)

fun main() {
//launch app
launch<GOLapp>()
}
