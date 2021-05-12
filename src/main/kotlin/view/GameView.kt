package view

import GameController
import javafx.geometry.Orientation
import javafx.stage.FileChooser
import tornadofx.*

class GameView : View("Game of Life") {
    private val controller: GameController by inject()


    override val root = borderpane {
        center {
            separator(Orientation.VERTICAL)
        }

        top = menubar{
            menu("File"){
                item("Open"){
                    action {
                        val jsonExtensionFilter = FileChooser.ExtensionFilter("JSON","*.json")
                        val path = chooseFile("Choose JSON to load", arrayOf(jsonExtensionFilter))
                        controller.stopSimulation()
                        controller.load(path.first().absolutePath)
                    }
                }
                item("Save") {
                    action {
                        val jsonExtensionFilter = FileChooser.ExtensionFilter("JSON","*.json")
                        val path = chooseFile("Save state", arrayOf(jsonExtensionFilter),null, FileChooserMode.Save)
                        controller.save(path.first().absolutePath)
                    }
                }
            }
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