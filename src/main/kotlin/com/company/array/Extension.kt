package com.company.array

inline fun <reified T>Array<T>.refactor(reactorFunc: (it: T) -> T): Array<T> {
    var result = emptyArray<T>()
    for(element in this) {
        result = result.plus(reactorFunc(element))
    }
    return result
}