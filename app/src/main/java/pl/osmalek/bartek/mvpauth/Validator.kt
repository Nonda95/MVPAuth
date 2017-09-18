package pl.osmalek.bartek.mvpauth

interface Validator<in T> {
    fun validate(element: T?): Boolean
}