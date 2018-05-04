package com.company.controller

import com.company.array.copyWithChange
import com.company.controller.exception.*
import com.company.model.validation.ImgExtensionValidator
import javafx.scene.image.Image
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class MainController : Controller() {
    private lateinit var imageFile: File
    private val suppImageExtensions = arrayOf(
            "png",
            "bmp",
            "jpg",
            "jpeg",
            "gif"
    )
    private val validator = ImgExtensionValidator(suppImageExtensions)

    val imagePath: String
        get() = if(::imageFile.isInitialized) imageFile.toURI().toURL().toString() else throw ImageFileNotInitializedException()
    val title: String
        get() = if(::imageFile.isInitialized) "IVO 1.0 - [" + imageFile.absoluteFile.path + "] MIT License Igor Santarek 2018" else "IVO 1.0 MIT License Igor Santarek 2018"

    // O(1)
    private fun initImageFileByProperty() {
        if(app.parameters.raw.size>0) {
            val filePath = app.parameters.raw[0]
            if(filePath.isEmpty()) throw EmptyFilePathException()
            val file = File(filePath)
            if(!file.isFile) throw NotFilePathException()
            if(!validator.isValid(file.extension)) throw NotValidImageFileException()
            imageFile = file
        } else {
            throw NoAppParametersException()
        }
    }

    // O(1)
    private fun initImageFileByDialog() {
        val fileChooser = FileChooser()
        fileChooser.title = "Open image"
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Supported image files.", suppImageExtensions.copyWithChange({ "*.$it" }).toList()))
        val file = fileChooser.showOpenDialog(primaryStage)
        if(file != null) imageFile = file
    }

    // O(1)
    private fun getImageFileDir(): File {
        return if(::imageFile.isInitialized) imageFile.absoluteFile.parentFile else throw ImageFileNotInitializedException()
    }

    // O(n), n - image file parent directory entries.
    fun nextImage(): Image {
        // I want to read dir entries every time i change image because changes can occur in images directory.
        val dirEntries = getImageFileDir().listFiles().filter { validator.isValid(it.extension) }
        val nextImgIndex = dirEntries.indexOfFirst { it.name == imageFile.name } + 1
        imageFile = if(nextImgIndex < dirEntries.size) {
            dirEntries[nextImgIndex]
        } else {
            dirEntries[0]
        }
        return Image(imagePath)
    }

    // O(n), n - image file parent directory entries.
    fun prevImage(): Image {
        // I want to read dir entries every time i change image because changes can occur in images directory.
        val dirEntries = getImageFileDir().listFiles().filter { validator.isValid(it.extension) }
        val prevImgIndex = dirEntries.indexOfFirst { it.name == imageFile.name } - 1
        imageFile = if(prevImgIndex >= 0) {
            dirEntries[prevImgIndex]
        } else {
            dirEntries[dirEntries.size-1]
        }
        return Image(imagePath)
    }

    init {
        try {
            initImageFileByProperty()
        } catch(e: Exception) {
            initImageFileByDialog()
        }
    }
}