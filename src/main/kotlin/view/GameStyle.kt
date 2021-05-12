package view

import javafx.scene.paint.Color
import tornadofx.*

class GameStyle : Stylesheet() {

    companion object {

        val cellButtonSize = 10.px

        val deadCell by cssclass()
        val aliveCell by cssclass()
        val gameSpace by cssclass()
    }


    init {
        gameSpace {
            button {
                backgroundRadius = multi(box(0.percent))
                prefHeight = cellButtonSize
                prefWidth = cellButtonSize
                maxHeight = cellButtonSize
                maxWidth = cellButtonSize
                minHeight = cellButtonSize
                minWidth = cellButtonSize
            }

            aliveCell {
                backgroundColor = multi(Color.BLACK)
            }

            deadCell {
                backgroundColor = multi(Color.WHITE)
            }

        }
    }

}