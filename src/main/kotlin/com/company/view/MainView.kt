package com.company.view

import com.company.controller.MainController
import com.company.controller.exception.ImageFileNotInitializedException
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ContentDisplay
import javafx.scene.effect.DropShadow
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.text.FontWeight
import tornadofx.*

class MainView : View() {
    private val controller: MainController by inject()
    private lateinit var leftBtn: Button
    private lateinit var rightBtn: Button
    private lateinit var fullscreenBtn: Button
    private lateinit var imgView: ImageView
    private lateinit var rotLeftBtn: Button
    private lateinit var rotRightBtn: Button

    override val root = stackpane {
        try {
            borderpane {
                center {
                    imgView = imageview(controller.imagePath) {
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
                        top {
                            rotLeftBtn = button {
                                graphic = svgpath("M261.397,17.983c-88.909,0-167.372,51.302-203.909,129.073L32.072,94.282L0,109.73l52.783,109.565l109.565-52.786" +
                                        "l-15.451-32.066L89.82,161.934c30.833-65.308,96.818-108.353,171.577-108.353c104.668,0,189.818,85.154,189.818,189.821" +
                                        "s-85.15,189.824-189.818,189.824c-61.631,0-119.663-30.109-155.228-80.539l-29.096,20.521" +
                                        "c42.241,59.87,111.143,95.613,184.324,95.613c124.286,0,225.407-101.122,225.407-225.419S385.684,17.983,261.397,17.983z") {
                                    stroke = Color.WHITE
                                    strokeWidth = 2.0
                                    fill = Color.WHITE
                                    effect = DropShadow()
                                    scaleX = 0.07
                                    scaleY = 0.07
                                    opacity = 0.0
                                }
                                action {
                                    imgView.rotate -= 90
                                }
                                onHover {
                                    rotLeftBtn.graphic.opacity = 1.0
                                }
                                onMouseExited = EventHandler<MouseEvent> {
                                    rotLeftBtn.graphic.opacity = 0.0
                                }
                                shortcut("q")
                                tooltip("Rotate left (Q)")
                                contentDisplay = ContentDisplay.GRAPHIC_ONLY
                                setMaxSize(0.0, 0.0)
                                setMinSize(0.0, 0.0)
                            }
                            style {
                                paddingTop = 50.0
                            }
                        }
                        center {
                            leftBtn = button {
                                graphic = svgpath("M23,0 L0,15 L23,30 L23,0 Z") {
                                    stroke = Color.WHITE
                                    strokeWidth = 1.5
                                    fill = Color.WHITE
                                    effect = DropShadow()
                                    opacity = 0.0
                                }
                                action {
                                    imgView.image = controller.prevImage()
                                    title = controller.title
                                }
                                onHover {
                                    leftBtn.graphic.opacity = 1.0
                                }
                                onMouseExited = EventHandler<MouseEvent> {
                                    leftBtn.graphic.opacity = 0.0
                                }
                                shortcut("left")
                                tooltip("Previous image (<-)")
                                contentDisplay = ContentDisplay.GRAPHIC_ONLY
                                setMaxSize(0.0, 0.0)
                                setMinSize(0.0, 0.0)
                            }
                            style {
                                paddingLeft = 50.0
                            }
                        }
                    }
                }
                right {
                    borderpane {
                        top {
                            rotRightBtn = button {
                                graphic = svgpath("M261.397,17.983c-88.909,0-167.372,51.302-203.909,129.073L32.072,94.282L0,109.73l52.783,109.565l109.565-52.786" +
                                        "l-15.451-32.066L89.82,161.934c30.833-65.308,96.818-108.353,171.577-108.353c104.668,0,189.818,85.154,189.818,189.821" +
                                        "s-85.15,189.824-189.818,189.824c-61.631,0-119.663-30.109-155.228-80.539l-29.096,20.521" +
                                        "c42.241,59.87,111.143,95.613,184.324,95.613c124.286,0,225.407-101.122,225.407-225.419S385.684,17.983,261.397,17.983z") {
                                    stroke = Color.WHITE
                                    strokeWidth = 2.0
                                    fill = Color.WHITE
                                    effect = DropShadow()
                                    scaleX = -0.07
                                    scaleY = 0.07
                                    opacity = 0.0
                                }
                                action {
                                    imgView.rotate += 90
                                }
                                onHover {
                                    rotRightBtn.graphic.opacity = 1.0
                                }
                                onMouseExited = EventHandler<MouseEvent> {
                                    rotRightBtn.graphic.opacity = 0.0
                                }
                                shortcut("e")
                                tooltip("Rotate right (E)")
                                contentDisplay = ContentDisplay.GRAPHIC_ONLY
                                setMaxSize(0.0, 0.0)
                                setMinSize(0.0, 0.0)
                            }
                            style {
                                paddingTop = 50.0
                            }
                        }
                        center {
                            rightBtn = button {
                                graphic = svgpath("M0,0 L23,15 L0,30 L0,0 Z") {
                                    stroke = Color.WHITE
                                    strokeWidth = 1.5
                                    fill = Color.WHITE
                                    effect = DropShadow()
                                    opacity = 0.0
                                }
                                action {
                                    imgView.image = controller.nextImage()
                                    title = controller.title
                                }
                                onHover {
                                    rightBtn.graphic.opacity = 1.0
                                }
                                onMouseExited = EventHandler<MouseEvent> {
                                    rightBtn.graphic.opacity = 0.0
                                }
                                shortcut("right")
                                tooltip("Next image (->)")
                                contentDisplay = ContentDisplay.GRAPHIC_ONLY
                                setMaxSize(0.0, 0.0)
                                setMinSize(0.0, 0.0)
                            }
                            style {
                                paddingRight = 50.0
                            }
                        }
                    }
                }
                center {
                    fullscreenBtn = button {
                        graphic = group {
                            svgpath("M0,5 L0,0 L5,0 M15,0 L20,0 L20,5 M20,15 L20,20 L15,20 M5,20 L0,20 L0,15") {
                                stroke = Color.WHITE
                                strokeWidth = 1.5
                                fill = Color.TRANSPARENT
                                scaleX = 2.0
                                scaleY = 2.0
                                effect = DropShadow()
                            }
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
                        shortcut("space")
                        tooltip("Toggle fullscreen (SPACE)")
                        contentDisplay = ContentDisplay.GRAPHIC_ONLY
                        setMaxSize(0.0, 0.0)
                        setMinSize(0.0, 0.0)
                    }
                }
            }
            alignment = Pos.TOP_LEFT
        } catch(e: ImageFileNotInitializedException) {
            label("IVO") {
                style {
                    textFill = Paint.valueOf("444")
                    fontSize = 72.px
                    fontWeight = FontWeight.BOLD
                }
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