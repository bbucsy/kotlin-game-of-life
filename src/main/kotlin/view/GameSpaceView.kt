package view

import javafx.scene.paint.Color
import model.GameModel
import tornadofx.*

/**
 * A view fragment that shows the state of a single GameModel
 */
class GameSpaceView : Fragment() {

    /**
     * The model to show, given by a parameter
     */
    val model: GameModel by param()

    /**
     * The size of a single cell
     */
    private val rectangleSize: Pair<Double, Double> = Pair(400.0 / model.n, 400.0 / model.m)

    override val root = gridpane {
        for (i in 0 until model.space.n) {
            for (j in 0 until model.space.m) {
                // make a cell
                rectangle {
                    width = rectangleSize.first
                    height = rectangleSize.second
                    fill = if (model.space[i, j].value) Color.BLACK else Color.WHITE

                    // set the position of the cell in the grid
                    gridpaneConstraints {
                        columnRowIndex(i, j)
                    }

                    // add event handler to onMouseClick event
                    setOnMouseClicked { model.space[i, j].invertValue() }

                    // add event handler to the BufferedCell's onChange event.
                    model.space[i, j].onChange = { _, alive ->
                        fill = if (alive) Color.BLACK else Color.WHITE
                    }
                }
            }
        }
    }
}
