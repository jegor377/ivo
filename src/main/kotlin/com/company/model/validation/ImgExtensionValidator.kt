package com.company.model.validation

class ImgExtensionValidator(private val validExtensions: Array<String>): Validator<String> {
    override fun isValid(element: String): Boolean {
        return validExtensions.contains(element)
    }
}