package view

import javafx.scene.paint.Color
import model.GameModel
import tornadofx.*

class GameSpaceView : Fragment() {
    val model: GameModel by param()
    private val rectangleSize: Pair<Double, Double> = Pair(400.0 / model.n, 400.0 / model.m)

    override val root = gridpane {
        for (i in 0 until model.space.n) {
            for (j in 0 until model.space.m) {
                rectangle {
                    width = rectangleSize.first
                    height = rectangleSize.second
                    fill = if (model.space[i, j].value) Color.BLACK else Color.WHITE

                    gridpaneConstraints {
                        columnRowIndex(i, j)
                    }
                    setOnMouseClicked { model.space[i, j].invertValue() }
                    model.space[i, j].onChange = { _, alive ->
                        fill = if (alive) Color.BLACK else Color.WHITE
                    }
                }
            }
        }
    }
}
