package com.company.view

import com.company.controller.MainController
import javafx.scene.paint.Paint
import tornadofx.*

class MainView : View() {
    private val controller: MainController by inject()

    override val root = borderpane {
        center {
            if(controller.imagePath != null) imageview(controller.imagePath) {
                fitWidthProperty().bind(primaryStage.widthProperty())
                fitHeightProperty().bind(primaryStage.heightProperty())
                isPreserveRatio = true
            }
        }
        style {
            backgroundColor += Paint.valueOf("222")
        }
    }

    init {
        title = controller.title
    }
}