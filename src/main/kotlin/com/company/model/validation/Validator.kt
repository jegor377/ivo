package com.company.model.validation

interface Validator<E> {
    fun isValid(element: E): Boolean
}