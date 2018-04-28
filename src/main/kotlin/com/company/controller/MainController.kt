package com.company.controller

import javafx.scene.image.ImageView
import tornadofx.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class MainController: Controller() {
    lateinit var dirEntries: Array<File>
    lateinit var headingImage: File
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

    fun loadDirEntries() {
        val file = File(app.parameters.raw[0])
        if(file.isFile) {
            val fileDir = file.parentFile
            dirEntries = fileDir.listFiles()
        }
    }

    fun nextImage(imgView: ImageView) {

    }

    fun prevImage(imgView: ImageView) {

    }
}