package view

import controller.GameController
import javafx.geometry.Orientation
import javafx.stage.FileChooser
import tornadofx.*
import java.lang.Exception

/**
 * The main view of the application
 *
 * Contains two GameSpaceView fragments, and all the buttons to control the simulation
 */
class GameView : View("Game of Life") {

    // Inject the game controller
    private val controller: GameController by inject()


    override val root = borderpane {

        // make a separator line between the two game spaces
        center { separator(Orientation.VERTICAL) }

        // make the menubar to save/load gamestates
        top = menubar {
            menu("File") {
                item("Open") {
                    action {
                        try {
                            val jsonExtensionFilter = FileChooser.ExtensionFilter("JSON", "*.json")
                            val path = chooseFile("Choose JSON to load", arrayOf(jsonExtensionFilter))
                            controller.stopSimulation()
                            controller.load(path.first())
                        } catch (e: Exception) {
                            println("Could not load file")
                        }
                    }
                }
                item("Save") {
                    action {
                        try {
                            val jsonExtensionFilter = FileChooser.ExtensionFilter("JSON", "*.json")
                            val path =
                                chooseFile("Save state", arrayOf(jsonExtensionFilter), null, FileChooserMode.Save)
                            controller.save(path.first())
                        } catch (e: Exception) {
                            println("Could not save file")
                        }
                    }
                }
            }
        }

        // add right side game space view
        right = stackpane { this += find<GameSpaceView>(mapOf(GameSpaceView::model to controller.modelA)) }

        // add left side game space view
        left = stackpane { this += find<GameSpaceView>(mapOf(GameSpaceView::model to controller.modelB)) }

        // add controlling buttons
        bottom = hbox {
            spacing = 10.0
            button("Start") { action { controller.startSimulation() } }
            button("Stop") { action { controller.stopSimulation() } }
            button("Speed++") { action { controller.speedUp() } }
            button("Speed--") { action { controller.speedDown() } }
            button("Clear") { action { controller.clear() } }
        }
    }


}