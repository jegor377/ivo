package com.company.model.validation

interface Validator<T> {
    fun isValid(element: T): Boolean
}