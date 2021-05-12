package view

import javafx.scene.paint.Color
import model.GameSpace
import tornadofx.*

class GameFragment() : Fragment() {
    val model: GameSpace by param()

    override val root = gridpane {

        for (i in 0 until model.space.n) {
            for (j in 0 until model.space.m) {
                addClass(GameStyle.gameSpace)
                button("") {
                    model.space[i, j].onChange = { _, alive ->
                        if (alive){
                            removeClass(GameStyle.deadCell)
                            addClass(GameStyle.aliveCell)
                        }
                        else {
                            removeClass(GameStyle.aliveCell)
                            addClass(GameStyle.deadCell)
                        }
                    }
                    action {
                        model.space[i, j].invertValue()
                    }
                    gridpaneConstraints {
                        columnRowIndex(i, j)
                    }
                    addClass(if (model.space[i,j].value) GameStyle.aliveCell else GameStyle.deadCell)
                }
            }
        }
    }
}