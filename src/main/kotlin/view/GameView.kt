package view

import GameController
import javafx.geometry.Orientation
import tornadofx.*

class GameView : View() {
    private val controller: GameController by inject()


    override val root = borderpane {
        center {
            separator(Orientation.VERTICAL)
        }

        right = stackpane {
            this += find<GameFragment>(mapOf(GameFragment::model to controller.modelA))
        }

        left = stackpane {
            this += find<GameFragment>(mapOf(GameFragment::model to controller.modelB))
        }

        bottom = flowpane {
            button("start") {
                action { controller.startSimulation() }
            }
            button("stop") {
                action { controller.stopSimulation() }
            }
            button("speed++") {
                action { controller.speedUp() }
            }
            button ("speed--"){
                action { controller.speedDown()  }
            }
        }
    }


}