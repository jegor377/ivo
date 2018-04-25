package com.company.controller

import tornadofx.*
import java.io.File

class MainController: Controller() {
    val imagePath: String?
        get() {
            if(app.parameters.raw.size==1) {
                val file = File(app.parameters.raw[0])
                return file.toURI().toURL().toString()
            }
            return null
        }
    val title = getTitle(app.parameters.raw.size)

    private fun getTitle(size: Int): String =
            if(size == 1) ("IVO - " + app.parameters.raw[0]) else "IVO"

    fun getImageWidth(windowWidth: Double): Double {
        return windowWidth
    }
}