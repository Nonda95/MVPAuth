package pl.osmalek.bartek.mvpauth.validators

interface Validator<in T> {
    fun validate(element: T?): Boolean
}