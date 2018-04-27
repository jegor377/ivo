package com.company.view

import com.company.controller.MainController
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ContentDisplay
import javafx.scene.effect.DropShadow
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import tornadofx.*

class MainView : View() {
    private val controller: MainController by inject()
    private lateinit var leftBtn: Button
    private lateinit var rightBtn: Button
    private lateinit var fullscreenBtn: Button

    override val root = stackpane {
        borderpane {
            center {
                if(controller.imagePath != null) imageview(controller.imagePath) {
                    fitWidthProperty().bind(primaryStage.widthProperty())
                    fitHeightProperty().bind(primaryStage.heightProperty())
                    isPreserveRatio = true
                }
            }
            useMaxHeight = true
            useMaxWidth = true
        }
        borderpane {
            left {
                borderpane {
                    center {
                        leftBtn = button {
                            graphic = svgpath("M23,0 L0,15 L23,30 L23,0 Z") {
                                stroke = Color.WHITE
                                strokeWidth = 1.5
                                fill = Color.WHITE
                            }
                            contentDisplay = ContentDisplay.GRAPHIC_ONLY
                            setMaxSize(0.0, 0.0)
                            setMinSize(0.0, 0.0)
                        }
                        style {
                            paddingLeft = 20.0
                        }
                    }
                }
            }
            right {
                borderpane {
                    center {
                        rightBtn = button {
                            graphic = svgpath("M0,0 L23,15 L0,30 L0,0 Z") {
                                stroke = Color.WHITE
                                strokeWidth = 1.5
                                fill = Color.WHITE
                            }
                            contentDisplay = ContentDisplay.GRAPHIC_ONLY
                            setMaxSize(0.0, 0.0)
                            setMinSize(0.0, 0.0)
                        }
                        style {
                            paddingRight = 20.0
                        }
                    }
                }
            }
            center {
                fullscreenBtn = button {
                    graphic = group {
                        /*circle {
                            centerX = 10.0
                            centerY = 10.0
                            radius = 28.0
                            fill = Color.BLACK
                        }*/
                        svgpath("M0,5 L0,0 L5,0 M15,0 L20,0 L20,5 M20,15 L20,20 L15,20 M5,20 L0,20 L0,15") {
                            stroke = Color.WHITE
                            strokeWidth = 1.5
                            fill = Color.TRANSPARENT
                        }
                        effect = DropShadow()
                        opacity = 0.0
                    }
                    action {
                        primaryStage.isFullScreen = !primaryStage.isFullScreen
                    }
                    onHover {
                        fullscreenBtn.graphic.opacity = 1.0
                    }
                    onMouseExited = EventHandler<MouseEvent> {
                        fullscreenBtn.graphic.opacity = 0.0
                    }
                    contentDisplay = ContentDisplay.GRAPHIC_ONLY
                    setMaxSize(0.0, 0.0)
                    setMinSize(0.0, 0.0)
                }
            }
        }
        alignment = Pos.TOP_LEFT
        style {
            backgroundColor += Paint.valueOf("222")
        }
    }

    init {
        title = controller.title
    }
}